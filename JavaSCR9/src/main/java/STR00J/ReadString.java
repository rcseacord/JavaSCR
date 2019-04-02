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

package STR00J;

import java.nio.charset.StandardCharsets;

class ReadString {

  public static void main(String[] args) {
    StringBuilder str = new StringBuilder();
    int offset; // initialized to zero

    // A = 41 Ω = CE  A9   語  = E8  AA  9E
    // Ugaritic letter delta = F0 90 8E 84
    byte[] utf8_data = {
        (byte) 0x41, (byte) 0xCE, (byte) 0xA9, (byte) 0xE8,
        (byte) 0xAA, (byte) 0x9E, (byte) 0xF0, (byte) 0x90,
        (byte) 0x8E, (byte) 0x84, (byte) 0x41, (byte) 0x41
    };

    // A = 0041 ß = 00DF
    // 東 = 6771  𐐀  = D801 DC00
    byte[] utf16_data = {
        (byte) 0x00, (byte) 0x41, (byte) 0x00, (byte) 0xDF,
        (byte) 0x67, (byte) 0x71, (byte) 0xD8, (byte) 0x01,
        (byte) 0xDC, (byte) 0x00, (byte) 0x00, (byte) 0x41
    };

    // Read UTF-8 Data from 4 byte buffer
    for (offset = 0; offset < utf8_data.length; offset += 4) {
      str.append(new String(utf8_data, offset, 4, StandardCharsets.UTF_8));
    }

    // convert full string
    String reference_utf8_str = new String(utf8_data, StandardCharsets.UTF_8);

    if (!reference_utf8_str.equals(str.toString())) {
      System.out.println("UTF-8 strings are not equal");
    }

    // Read UTF-16 Data from 4 byte buffer
    str = new StringBuilder();
    for (offset = 0; offset < utf16_data.length; offset += 4) {
      str.append(new String(utf16_data, offset, 4, StandardCharsets.UTF_16));
    }

    // print full string
    String reference_utf16_str = new String(utf16_data, StandardCharsets.UTF_16);

    if (!reference_utf16_str.equals(str.toString())) {
      System.out.println("UTF-16 strings are not equal");
    }

  } // main
} // class REadString
