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

package ser03j;

import java.io.*;

class Malicious {

  private static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(ba)) {
      oos.writeObject(o);
      return ba.toByteArray();
    }
  }

  private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
    Object obj;
    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
      obj = ois.readObject();
    }
    return obj;
  }

  /**
   * Copies a specified Object by serializing then deserializing the object.
   * <p>
   * This method should not be used in production code.
   *
   * @param obj the Object to copy
   * @return copy of obj
   */
  private static Object serialCopy(Object obj) throws IOException, ClassNotFoundException {
    return deserialize(serialize(obj));
  } // end serialCopy()

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Singleton sc = (Singleton) serialCopy(Singleton.getInstance());
    // Prints false; indicates new instance
    //noinspection NumberEquality
    System.out.println(sc == Singleton.getInstance());
    System.out.println("Balance = " + sc.getBalance());
  }

}