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

package OBJ03J;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class listUtility {

	// Raw object added to parameterized list
	private static void addToList(List list, Object obj) {
		list.add(obj); // Unchecked warning
	}

	// Correctly typed object added to parameterized list.
	/*
	private static void addToList(List<String> list, String obj) {
		list.add(obj); // Unchecked warning }
	}
	*/

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		// The call to addToList(list, 42) succeeds in adding an integer to
		// list, although it is of type List<String>.
		addToList(list, 42);

		// This Java runtime does not throw a ClassCastException until the value
		// is read and has an invalid type (an int rather than a String).
		// System.out.println(list.get(0)); // Throws ClassCastException

		// If the addToList() method is legacy code that cannot be
		// changed, you can create a checked list view 
		// by using the Collections.checkedList() method. This method
		// returns a wrapper collection that performs runtime type checking in
		// its implementation of the add() method before delegating to the
		// back-end List<String>.
		List<String> backlist = new ArrayList<>();
		List<String> checkedList = Collections.checkedList(backlist, String.class);
		addToList(checkedList, 42);
		System.out.println(list.get(0));

	}
}