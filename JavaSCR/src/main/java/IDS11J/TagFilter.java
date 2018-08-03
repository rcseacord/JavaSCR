// The MIT License (MIT)
// 
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

package IDS11J;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TagFilter {

	private static String filterString(String str) {
		String s = Normalizer.normalize(str, Form.NFKC);

		// Validate input
		Pattern pattern = Pattern.compile("<script>");
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			throw new IllegalArgumentException("Invalid input");
		}

		// Remove all noncharacter code points
		s = s.replaceAll("[\\p{Cn}]", "");
		return s;
	}

	public static void main(String[] args) {
		// "\uFDEF" is a noncharacter code point
		String maliciousInput = "<scr" + "\uFDEF" + "ipt>";
		String sb = filterString(maliciousInput);
		System.out.println(sb);
	}

}








































//	private static String filterString(String str) {
//		String s = Normalizer.normalize(str, Form.NFKC);
//
//		// Replaces all noncharacter code points with Unicode U+FFFD
//		s = s.replaceAll("[\\p{Cn}]", "\uFFFD");
//
//		// Validate input
//		Pattern pattern = Pattern.compile("<script>");
//		Matcher matcher = pattern.matcher(s);
//		if (matcher.find()) {
//			throw new IllegalArgumentException("Invalid input");
//		}
//		return s;
//	}