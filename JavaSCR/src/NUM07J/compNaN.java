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

package NUM07J;

class compNaN {

	public static void main(String[] args) {
		// Do not perform a direct comparisons with�NaN
		double x = 0.0;
		double result = Math.cos(1 / x); // returns NaN
		// comparison is always false!
		if (result == Double.NaN) {
			System.out.println("result is NaN"); //$NON-NLS-1$
		}

		// Use the�isNaN() method to check whether the specified value is�NaN
		if (Double.isNaN(result)) {
			System.out.println("result is NaN"); //$NON-NLS-1$
		}

		// true if and only if x is NaN
		if (result != result) {
			System.out.println("result is NaN"); //$NON-NLS-1$
		}
		
		x = Math.cos(1 / x); // returns NaN
		double y = Math.cos(1 / x); // returns NaN
		
		// false if x or y is NaN
		if ((x<y) == !(x>=y)) {
			System.out.println("x and y are not NaN"); //$NON-NLS-1$
		}
		else {
			System.out.println("result is NaN"); //$NON-NLS-1$
		}	
	}
}
