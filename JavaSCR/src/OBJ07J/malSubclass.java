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

package OBJ07J;

class malSubclass extends sensitiveClass implements Cloneable {
	protected malSubclass(String filename) {
		super(filename);
	}

	@Override
	public malSubclass clone() { // Well-behaved clone() method
		malSubclass s = null;
		try {
			s = (malSubclass) super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("not cloneable");
		}
		return s;
	}

	public static void main(String[] args) {
		// Java's cloning feature provides a way to circumvent the sharing
		// constraint even though SensitiveClass does not implement the
		// Cloneable interface.
// malSubclass ms1 = new malSubclass("file.txt");
		malSubclass ms1 = new malSubclass("file.txt");
		malSubclass ms2 = ms1.clone(); // Creates a copy by cloning ms1
		String s = ms1.get(); // Returns filename
		System.out.println(s); // Filename is "file.txt"
		// Because the second instance ms2 does not have its shared flag set to
		// true, it is possible to alter the first instance ms1 using the
		// replace() method.
		ms2.replace(); // Replaces all characters with 'x'
		// ms1.get() and ms2.get() will subsequently return filename =
		// 'xxxxxxxx'
		ms1.printFilename(); // Filename becomes 'xxxxxxxx'
		ms2.printFilename(); // Filename becomes 'xxxxxxxx'
	}
}