// The MIT License (MIT)
// 
// Copyright (c) 2025 Robert C. Seacord
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

package infoLeak;

import java.util.Calendar;
import java.util.GregorianCalendar;

class Formatter {

	final private static Calendar c = new GregorianCalendar(2028, Calendar.MAY, 23);

	// args[0] should contain the credit card expiration date
	// but can contain either %1$tm, %1$te or %1$tY as malicious arguments
	public static void main(String[] args) {

		String argument = "%1$tm %1$te,%1$tY";
		// First argument prints 05 (May), second prints 23 (day) and third
		// prints 2028 (year)
		System.out.format(argument + " did not match! HINT: It was issued on %1$terd of some month%n", c);

		// Conversion specifications are now inert
		System.out.format("%s did not match! HINT: It was issued on %terd of some month%n", argument, c);
	}

}
