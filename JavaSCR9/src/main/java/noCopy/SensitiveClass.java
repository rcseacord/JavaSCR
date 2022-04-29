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

package noCopy;

class SensitiveClass {
	final private char[] filename;
	private Boolean shared = false;

	SensitiveClass(String filename) {
		this.filename = filename.toCharArray();
	}

	// When a client requests a String instance by invoking the get() method,
	// the shared flag is set. To maintain the array's consistency with the
	// returned String object, operations that can modify the array are
	// subsequently prohibited.
	final String get() {
		if (!this.shared) {
			this.shared = true;
			return String.valueOf(this.filename);
		} else {
			throw new IllegalStateException("Failed to get instance"); //$NON-NLS-1$
		}
	}

	// The replace method will not replace all elements of the array with
	// an x when the shared flag is set.
	final void replace() {
		if (!this.shared) {
			for (int i = 0; i < this.filename.length; i++) {
				this.filename[i] = 'x';
			}
		}
	}
	
	final void print() {
		System.out.println(String.valueOf(this.filename));
	}
	
	// Prevent subclasses from being made cloneable by defining a final clone
	// method that always fails.
//	public final Object clone() throws CloneNotSupportedException {
//		throw new CloneNotSupportedException();
//	}

	public static void main(String[] args) {
		SensitiveClass ms1 = new SensitiveClass("password.txt");
		String s = ms1.get(); // Returns filename
		System.out.println(s); // Filename is "file.txt"
		ms1.replace(); // Attempts to replace all characters with 'x'
		ms1.print(); // Filename unchanged
	}
}