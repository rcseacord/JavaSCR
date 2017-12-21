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

package NUM03J;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

class UnsignedInteger {

  // @return int read from specified DataInputStream
  private static int getIntegerWrong(DataInputStream dis) throws IOException {
    return dis.readInt();
  }

  // @return long read from specified DataInputStream
  private static long getInteger(DataInputStream dis) throws IOException {
    // The readInt() method assumes signed values and returns a signed int;
    // the return value is converted to a long by sign extension. The code
    // uses an & operation to mask off the upper 32 bits of the long,
    // producing a value in the range of a 32-bit unsigned integer, as
    // intended.
    return dis.readInt() & 0xFFFFFFFFL; // mask with 32 one-bits
  }

  // @return long read from specified DataInputStream
  private static long getULong(DataInputStream dis) throws IOException {
    return dis.readLong();
  }

  public static void main(String[] args) throws IOException {
    try (
            // create output stream from file
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("test.txt"))//$NON-NLS-1$
    ) {
      // write out "unsigned int" values
      dos.writeInt(0xFFFFFFFF); // 4,294,967,295
      dos.writeInt(0xFFFFFFFF); // 4294967295
      dos.writeInt(0xFFFFFFFF); // 4294967295
      dos.writeInt(0xFFFFFFFF); // 4294967295
    }
    // create input stream from file input stream
    FileInputStream is = new FileInputStream("test.txt"); //$NON-NLS-1$

    // create data input stream
    try (DataInputStream dis = new DataInputStream(is)) {
      int int_a = getIntegerWrong(dis);
      System.out.println("incorrect value = " + int_a); //$NON-NLS-1$

      long long_a = getInteger(dis);
      System.out.println("correct value = " + long_a); //$NON-NLS-1$

      // answer should be 18446744073709551615UL
      Long long_bi = getULong(dis);
      BigInteger bi = new BigInteger(long_bi.toString());
      // add 2^^64 if the sign bit is set
      if (bi.testBit(63))
        bi = bi.add(new BigInteger("2").pow(64)); //$NON-NLS-1$
      System.out.println("bi = " + bi); //$NON-NLS-1$
    }
    // ---------- Add code here -----------------------------

  }
}






















/*
 * // Java SE 8 // Convert to unsigned string with a radix value of 10
 * System.out.println("Unsigned Integer Max Value = " +
 * Integer.toUnsignedString(int_a));
 * System.out.println("Unsigned Long Max Value = " +
 * Long.toUnsignedString(long_bi));
 * 
 * // Divide Integer.MAX_VALUE by int_a (signed and unsigned)
 * System.out.println("Signed quotient = " + Integer.MAX_VALUE / int_a);
 * System.out.println("Unsigned quotient = " +
 * Integer.divideUnsigned(Integer.MAX_VALUE, int_a));
 */
