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

package NUM08J;

public class FPinput {

	static double currentBalance; // User's cash balance

	static void doDepositWrong(String userInput) {
		double val = 0;
		try {
			val = Double.valueOf(userInput);
		} catch (NumberFormatException e) {
			System.err.println("input format error");
			return;
		}

		if (val >= Double.MAX_VALUE - currentBalance) {
			System.err.println("input range error");
			return;
		}
		System.err.println("updating balance");
		currentBalance += val;
	}

	static void doDeposit(String userInput) {
		double val = 0;
		try {
			val = Double.valueOf(userInput);
		} catch (NumberFormatException e) {
			System.err.println("input format error");
			return;
		}

		if (Double.isInfinite(val)) {
			System.err.println("input infinity error");
			return;
		}

		if (Double.isNaN(val)) {
			System.err.println("input NaN error");
			return;
		}

		if (val >= Double.MAX_VALUE - currentBalance) {
			System.err.println("input range error");
			return;
		}
		System.err.println("updating balance");
		currentBalance += val;
	}

	public static void main(String[] args) {
		doDepositWrong("-Infinity");
		doDepositWrong("Infinity");
		doDepositWrong("NaN");
		doDeposit("-Infinity");
		doDeposit("Infinity");
		doDeposit("NaN");
	}

}
