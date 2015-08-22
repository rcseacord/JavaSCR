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

package NUM12J;

public class Conversions {

	public static void narrowIntToByteWrong(int i) {
		byte b = (byte) i; // b has value -128
		System.out.println("b = " + b);
	}

	public static void narrowIntToByte(int i) throws ArithmeticException {
		// check if i is within byte range
		if ((i < Byte.MIN_VALUE) || (i > Byte.MAX_VALUE)) {
			throw new ArithmeticException("Int value is out of range");
		}

		byte b = (byte) i;
		System.out.println("b = " + b);
	}

	public static void fpToIntWrong(float f) {
		short s = (short) f;
		System.out.println("s = " + s);
	}

	public static void fpToInt(float f) throws ArithmeticException {
		if ((f < Short.MIN_VALUE) || (f > Short.MAX_VALUE)) {
			  throw new ArithmeticException ("Float value is out of range");
			}
		short s = (short)f;
		System.out.println("s = " + s);
	}

	public static void main(String[] args) {
		// Integer narrowing
		narrowIntToByteWrong(128);
		try {
			narrowIntToByte(128);
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage());
		}
		
		// Floating-Point to Integer Conversion
		fpToIntWrong(Float.MAX_VALUE);
		try {
			fpToInt(Float.MAX_VALUE);
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage());
		}
	}
}