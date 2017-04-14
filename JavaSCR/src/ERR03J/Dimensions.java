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

package ERR03J;

import ERR01J.ExceptionReporter;

class Dimensions {
	private int l, w, h;
	private static final int PAD = 2;
	static public final int MAX_DIM = 12;

	private Dimensions(int l, int w, int h) {
		this.l = l;
		this.w = w;
		this.h = h;
		/*
		if (l <= PAD || w <= PAD || h <= PAD || l > MAX_DIM + PAD ||
			w > MAX_DIM + PAD || h > MAX_DIM + PAD) {
			throw new IllegalArgumentException();
		} 
		*/
	}

	protected int getVolumePackage(int weight) {
		l += PAD; w += PAD; h += PAD;
		try {
			if (weight <= 0 || weight > 20) 
				throw new IllegalArgumentException();
			int volume = l * w * h; // 12 * 12 * 12 = 1728
			l -= PAD;
			w -= PAD;
			h -= PAD; // Revert
			return volume;
		} catch (Throwable t) {
			ExceptionReporter er = new ExceptionReporter();
			er.reportException(t);  // Report
			return -1; // Non-positive error code
		}
	}
	
	private int getVolumePackageGood(int weight) {
			try {
			if (weight <= 0 || weight > 20) 
				throw new IllegalArgumentException();
			return (l + PAD) * (w + PAD) * (h + PAD); 
		} catch (Throwable t) {
			ExceptionReporter er = new ExceptionReporter();
			er.reportException(t);  // Report
			return -1; // Non-positive error code
		}
	}

	public static void main(String[] args) {
		Dimensions d = new Dimensions(10, 10, 10);
		System.out.println(d.getVolumePackageGood(21)); // Prints -1 (error)
		System.out.println(d.getVolumePackageGood(19)); // Prints 2744 instead of 1728
	}
}