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
		String str = new String("Aß東𐐀001");

		String s = trim(str);
		System.out.println(s);
	}

}









































/*public static String trim(String string) {
	int ch;
	int i;
	for (i = 0; i < string.length(); i += Character.charCount(ch)) {
		ch = string.codePointAt(i);
		if (!Character.isLetter(ch)) {
			break;
		}
	}
	return string.substring(i);
}*/
