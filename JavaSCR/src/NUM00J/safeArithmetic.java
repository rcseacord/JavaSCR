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

package NUM00J;

import java.lang.Integer;

public class safeArithmetic {
	static final int safeAdd(int left, int right) throws ArithmeticException {
		if (right > 0 ? left > Integer.MAX_VALUE - right
				: left < Integer.MIN_VALUE - right) {
			throw new ArithmeticException("Integer overflow");
		}
		return left + right;
	}

	static final int safeSubtract(int left, int right)
			throws ArithmeticException {
		if (right > 0 ? left < Integer.MIN_VALUE + right
				: left > Integer.MAX_VALUE + right) {
			throw new ArithmeticException("Integer overflow");
		}
		return left - right;
	}

	static final int safeMultiply(int left, int right)
			throws ArithmeticException {
		if (right > 0 ? left > Integer.MAX_VALUE / right
				|| left < Integer.MIN_VALUE / right
				: (right < -1 ? left > Integer.MIN_VALUE / right
						|| left < Integer.MAX_VALUE / right : right == -1
						&& left == Integer.MIN_VALUE)) {
			throw new ArithmeticException("Integer overflow");
		}
		return left * right;
	}

	static final int safeDivide(int left, int right) throws ArithmeticException {
		if ((left == Integer.MIN_VALUE) && (right == -1)) {
			throw new ArithmeticException("Integer overflow");
		}
		return left / right;
	}

	static final int safeNegate(int a) throws ArithmeticException {
		if (a == Integer.MIN_VALUE) {
			throw new ArithmeticException("Integer overflow");
		}
		return -a;
	}

	static final int safeAbs(int a) throws ArithmeticException {
		if (a == Integer.MIN_VALUE) {
			throw new ArithmeticException("Integer overflow");
		}
		return Math.abs(a);
	}

	static public void main(String[] args) {
		try {
			System.out.println(safeAdd(5, 10));
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage());
		}
		
		System.out.println(5 + Integer.MAX_VALUE);
		
		try {
			System.out.println(safeAdd(5, Integer.MAX_VALUE));
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage());
		}		
		
		System.out.println(-5 + Integer.MIN_VALUE);
		
		try {
			System.out.println(safeAdd(-5, Integer.MIN_VALUE));
		} catch (ArithmeticException ae) {
			System.err.println("ArithmeticException: " + ae.getMessage());
		}
	}
}
