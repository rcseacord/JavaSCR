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

package EXP02J;

import java.util.Arrays;

public class ArrayEquals {

	public static void main(String[] args) {
		int[] arr1 = new int[20]; // initialized to 0
		int[] arr2 = new int[20]; // initialized to 0
		
		// Object.equals() compares array references; not contents.
		System.out.println(arr1.equals(arr2)); // false
		
		// Use the two-argument Arrays.equals() method to 
		// compare the contents of two arrays.
		System.out.println(Arrays.equals(arr1, arr2)); // true
		
		// Use the reference equality operators 
		// to compare references.
		System.out.println(arr1 == arr2); // false
	}

}
