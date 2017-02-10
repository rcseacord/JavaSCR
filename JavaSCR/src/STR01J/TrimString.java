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

package STR01J;

public class TrimString {

	public static String trim(String string) {
		char ch;
		int i;
		for (i = 0; i < string.length(); i += 1) {
			ch = string.charAt(i);
			if (!Character.isLetter(ch)) {
				break;
			}
		}
		return string.substring(i);
	}

	public static void main(String[] args) {
		String s1 = trim("Aß東𐐀001");
		System.out.println(s1);
		String s2 = trim("𐐀𐐀𐐀𐐀𐐀𐐀𐐀𐐀1");
		System.out.println(s2);
	}

}









































/*
    // Fast solution 
  	public static String trim(String string) {
		boolean nonLetterFound = false;
		StringBuilder sb = new StringBuilder(string.length());
		for (int ch : (Iterable<Integer>) string.codePoints()::iterator) {
			if (nonLetterFound) {
				sb.appendCodePoint(ch);
			} else {
				if (!Character.isLetter(ch)) {
					nonLetterFound = true;
					sb.appendCodePoint(ch);
				}
			}
		}
		return sb.toString();
	}
	
	// Simpler solution 
    public static String trim(String string) {
        int i = 0;
        // For each code point in string. The codePoints() method returns  
        // a stream of code point values from this sequence.
        // Any surrogate pairs encountered in the sequence are combined as  
        // if by Character.toCodePoint and the result is passed to the stream. 
        for (int ch : (Iterable<Integer>)string.codePoints()::iterator) {
            // determine if code point is character
            if (!Character.isLetter(ch)) {
                break;
            }
            i++;
        }     
        // offsetByCodePoints returns the ith code point 
        return string.substring(string.offsetByCodePoints(0, i));
     }
*/
