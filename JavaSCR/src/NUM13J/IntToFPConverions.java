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

package NUM13J;

class IntToFPConverions {

	private static int subFloatFromIntWrong(int op1, float op2) {
		return op1 - (int) op2;
	}

	private static int subFloatFromInt(int op1, float op2)
			throws ArithmeticException {

		// The significand can store at most 23 bits
		if ((op2 > 0x007fffff) || (op2 < -0x800000)) {
			throw new ArithmeticException("Insufficient precision");
		}

		return op1 - (int) op2;
	}

	public static void main(String[] args) {
		int result = subFloatFromIntWrong(1234567890, 1234567890);
		System.out.println(result); // Prints -46 (not 0)

		try {
			result = subFloatFromInt(1234567890, 1234567890);
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage());
		}
		System.out.println(result);
	}

}
