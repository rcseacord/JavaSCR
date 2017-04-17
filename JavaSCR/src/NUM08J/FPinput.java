// The MIT License (MIT)
// 
// Copyright (c) 2017 Robert C. Seacord
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

class FPinput {

	private static double currentBalance; // User's cash balance

	private static void doDeposit(String userInput) {
		double val; // initialized to zero
		try {
			val = Double.valueOf(userInput);
		} catch (NumberFormatException e) {
			System.err.println("input format error");
			return;
		}
		
		if (!Double.isFinite(val)) {
			System.err.println("value is not finite");
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
		doDeposit("-Infinity");
		doDeposit("Infinity");
		doDeposit("NaN");
		doDeposit("-infinity");
		doDeposit("infinity");
		doDeposit("nan");
	}

}

































/*
		// Returns true if the argument is a finite floating-point value; 
		// returns false otherwise (for NaN and infinity arguments).
		if (!Double.isFinite(val)) {
			System.err.println("value is not finite");
			return;
		}

		if (Double.isInfinite(val)) {
			System.err.println("input is infinite");
			return;
		}
		
		if (Double.isNaN(val)) {
			System.err.println("input is NaN");
			return;
		}

		if (val >= Double.MAX_VALUE - currentBalance) {
			System.err.println("input range error");
			return;
		}
		System.err.println("updating balance");
		currentBalance += val;
}*/
