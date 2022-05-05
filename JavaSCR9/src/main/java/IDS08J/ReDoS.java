// The MIT License (MIT)
//
// Copyright (c) 2022 Robert C. Seacord
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

package IDS08J;

class ReDoS {
	public static void main(String[] args) {
		final String a002 = "aa"; // two 'a'
		final String a003 = "aaa";  // three 'a'
		// 102 'a'
		final String a102 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		// 103 'a'
		final String a103 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

		final String ax4 = "aaaaX";
		final String ax16 = "aaaaaaaaaaaaaaaaX";
		final String ax32 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX";
		
		System.out.println(ax4.matches("^(a+)+$"));
		System.out.println(ax16.matches("^(a+)+$"));
	    System.out.println(ax32.matches("^(a+)+$"));

		System.out.println(a103.matches("(a|a)*"));

		System.out.println(a103.matches("(a|a)+$"));
		
		System.out.println(a002.matches("(aa|aab?)*"));
		System.out.println(a003.matches("(aa|aab?)*"));
		System.out.println(a102.matches("(aa|aab?)*"));
		System.out.println("++++++++++++++++++++++");
		System.out.println(a103.matches("(aa|aab?)*"));
		System.out.println("++++++++++++++++++++++");
	}
}
