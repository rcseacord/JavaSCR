// The MIT License (MIT)
// 
// Copyright (c) 2016 Robert C. Seacord
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

package STR03J;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

class ByteToString {

  private static BigInteger StringToBigInteger(String s) throws UnsupportedEncodingException {
    String ns;
    byte[] byteArray = s.getBytes(StandardCharsets.UTF_8);
    ns = new String(byteArray, StandardCharsets.UTF_8);
    return new BigInteger(ns);
  }

  public static void main(String[] args) throws UnsupportedEncodingException {
    // Generate binary data instead of string data
    BigInteger x = new BigInteger("530500452766");
    byte[] byteArray = x.toByteArray();
    String s = new String(byteArray);
    try {
      x = StringToBigInteger(s);
    } catch (java.lang.NumberFormatException nfe) {
      nfe.printStackTrace();
    }
    
    x = new BigInteger("530500452766");
    s = x.toString(); // valid character data
    x = StringToBigInteger(s);
    System.out.println(x);
  }
}
