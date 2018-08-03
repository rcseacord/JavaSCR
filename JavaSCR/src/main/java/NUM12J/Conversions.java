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

package NUM12J;

class Conversions {

	private static void narrowIntToByteWrong(int i) {
		byte b = (byte) i; // b has value -128
		System.out.println("b = " + b); //$NON-NLS-1$
	}

	private static void narrowIntToByte(int i) throws ArithmeticException {
		// check if i is within byte range
		if ((i < Byte.MIN_VALUE) || (i > Byte.MAX_VALUE)) {
			throw new ArithmeticException("Int value is out of range"); //$NON-NLS-1$
		}

		byte b = (byte) i;
		System.out.println("b = " + b); //$NON-NLS-1$
	}

	private static void fpToIntWrong(float f) {
		// Converted to int then narrowed to short
		short s = (short) f;
		System.out.println("(short) s = " + s); //$NON-NLS-1$
		
		// Converted to Integer.MAX_VALUE
		int i = (int) f;
		if (i == Integer.MAX_VALUE) {
			System.out.println("i = Integer.MAX_VALUE"); //$NON-NLS-1$
		}
		
		// Converted to Long.MAX_VALUE
		long l = (long) f;
		if (l == Long.MAX_VALUE) {
			System.out.println("l = Long.MAX_VALUE"); //$NON-NLS-1$
		}
	}

	private static void fpToInt(float f) throws ArithmeticException {
		if ((f < Short.MIN_VALUE) || (f > Short.MAX_VALUE)) {
			  throw new ArithmeticException ("Float value is out of range"); //$NON-NLS-1$
			}
		short s = (short)f;
		System.out.println("s = " + s); //$NON-NLS-1$
	}

	public static void main(String[] args) {
		// Integer narrowing
		narrowIntToByteWrong(128);
		try {
			narrowIntToByte(128);
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage()); //$NON-NLS-1$
		}
		
		// Floating-Point to Integer Conversion
		System.out.println(Float.MAX_VALUE);
		fpToIntWrong(Float.MAX_VALUE);
		try {
			fpToInt(Float.MAX_VALUE);
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage()); //$NON-NLS-1$
		}
	}
}