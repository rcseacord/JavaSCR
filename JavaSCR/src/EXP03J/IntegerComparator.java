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

package EXP03J;

import java.util.Comparator;

public class IntegerComparator {

	// The == and != operators should not be used to compare boxed primitives.
	private static Comparator<Integer> cmpWrong = new Comparator<Integer>() {
		public int compare(Integer i, Integer j) {
			return i < j ? -1 : (i == j ? 0 : 1);
		}
	};

	// The comparison operators <, >, <=, or >=, cause automatic unboxing
	// of primitive values.
	private static Comparator<Integer> cmp = new Comparator<Integer>() {
		public int compare(Integer i, Integer j) {
			return i < j ? -1 : (i > j ? 1 : 0);
		}
	};

	public static void main(String[] args) {
		System.out.println("cmpWrong less than: "
				+ cmpWrong.compare(2147483646, 2147483647));
		System.out.println("cmpWrong equal to: "
				+ cmpWrong.compare(2147483647, 2147483647));
		System.out.println("cmpWrong greater than: "
				+ cmpWrong.compare(2147483647, 2147483646));

		System.out.println("cmp less than: "
				+ cmp.compare(2147483646, 2147483647));
		System.out.println("cmp equal to: "
				+ cmp.compare(2147483647, 2147483647));
		System.out.println("cmp greater than: "
				+ cmp.compare(2147483647, 2147483646));

		// Constructors for class Boolean return distinct objects.
		Boolean b1 = new Boolean("true");
		Boolean b2 = new Boolean("true");

		if (b1 != b2) { // never equal
			System.out.println("Never equal");
		}

		// The values of autoboxed Boolean variables may be compared using the
		// reference equality operators because Java guarantees that the 
		// Boolean type is fully memoized.
		b1 = true;
		b2 = true;
		if (b1 == b2) { // always equal
			System.out.println("Always equal");
		}
		
		b1 = Boolean.TRUE;
		if (b1 == b2) { // always equal
			System.out.println("Always equal");
		}

	}

}
