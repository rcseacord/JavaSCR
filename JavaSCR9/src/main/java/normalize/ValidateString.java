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

package normalize;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class ValidateString {

	private static String NormalizeThenValidate(String input) {

		// Validate
		Pattern pattern = Pattern.compile("[<>]"); // Check for angle brackets
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			// Found black listed tag
			throw new IllegalStateException();
		}
		System.out.println("valid input");

		// Normalize
		return Normalizer.normalize(input, Form.NFKC);
	}

	public static void main(String[] args) {
		// Assume input is user controlled
		// \uFE64 is normalized to < and \uFE65 is normalized to > using the
		// NFKC normalization form
		String input = "\uFE64" + "script" + "\uFE65";
		System.out.println("non-normalized string: " + input);
		input = NormalizeThenValidate(input);
		System.out.println("normalized string: " + input);
	}
}






































/*
 public class ValidateString {

	public static Boolean NormalizeThenValidate(String s) {
		// Normalize
		s = Normalizer.normalize(s, Form.NFKC);
		// Validate
		Pattern pattern = Pattern.compile("[<>]"); // Check for angle brackets
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			// Found black listed tag
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		// Assume input is user controlled
		// \uFE64 is normalized to < and \uFE65 is normalized to > using the
		// NFKC normalization form
		String input = "\uFE64" + "script" + "\uFE65";
		System.out.println("unnormalized string: " + input);
		Boolean valid = NormalizeandValidate(input);
		if (valid) {
		  System.out.println("valid string: " + input);
		}
	} // end main
} // end class ValidateString
*/