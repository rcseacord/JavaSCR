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
		List<String> list = new ArrayList<String>();
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
		List<String> backlist = new ArrayList<String>();
		List<String> checkedList = Collections.checkedList(backlist, String.class);
		addToList(checkedList, 42);
		System.out.println(list.get(0));

	}
}