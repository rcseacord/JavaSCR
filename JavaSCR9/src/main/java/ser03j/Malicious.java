// The MIT License (MIT)
// 
// Copyright (c) 2019 Robert C. Seacord
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

import serial.Serial;

import java.io.*;

class Malicious {

  /**
   * Copies a specified Object by serializing then deserializing the object.
   * <p>
   * This method should not be used in production code.
   *
   * @param obj the Object to copy
   * @return copy of obj
   */
  private static Object serialCopy(Object obj) throws IOException, ClassNotFoundException {
    return Serial.deserialize(Serial.serialize(obj));
  } // end serialCopy()

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Singleton sc = (Singleton) serialCopy(Singleton.getInstance());

    //noinspection NumberEquality
    System.out.println(sc == Singleton.getInstance()); // Prints false indicating new instance
    System.out.println("Balance = " + sc.getBalance());
  }

}