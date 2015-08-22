// The MIT License (MIT)
// 
// Copyright (c) 2015 Secure Coding Institute
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

package STR05J;

import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnmappableCharacterException;

public class CharsetConversion {

  /*
   * static CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder(); public
   * static ByteBuffer encode(CharBuffer cb) throws CharacterCodingException {
   * return encoder.encode(cb); }
   */

  public static void main(String[] args) {
    CharBuffer charBuffer;
    CharsetDecoder decoder = StandardCharsets.UTF_16.newDecoder();

    // Use String constructor
    BigInteger x = new BigInteger("530500452766");
    byte[] byteArray = x.toByteArray();
    String s = new String(byteArray);
    System.out.println(s);
 
    // Use CharsetDecoder
    ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

    try {
      charBuffer = decoder.decode(byteBuffer);
      s = charBuffer.toString();
      System.out.println(s);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (MalformedInputException e) {
      e.printStackTrace();
    } catch (UnmappableCharacterException e) {
      e.printStackTrace();
    } catch (CharacterCodingException e) {
      e.printStackTrace();
    }
  }

}
