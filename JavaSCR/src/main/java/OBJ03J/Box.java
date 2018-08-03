// The MIT License (MIT)
//
// Copyright (c) 2018 Robert C. Seacord
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
import java.util.List;

/**
 * Generic version of the Box class.
 * @param <T> the type of the value being boxed
 */

// @SuppressWarnings("unchecked")

class Box<T> {
    // T stands for "Type"
    private T t;

    void set(T t) { this.t = t; }
    public T get() { return t; }
    
	public static void main(String[] args) {
    // invocation of the set method is allowed if the argument is compatible with Number
    Box<Number> box = new Box<>();
    Integer i = 10;
    Double d = 10.1;
    box.set(i);   // OK
    box.set(d);  // OK

		Box<Integer> intBox = new Box<>();
		intBox.set(1);
		
		// If the actual type argument is omitted, a raw type of Box<T> is created:
		Box rawBox;
		
		// For backward compatibility, assigning a parameterized type to its raw type is allowed:
		Box<String> stringBox = new Box<>();
		rawBox = stringBox;       // OK
		rawBox.set(8);  // warning: unchecked invocation to set(T)

		// Assigning a raw type to a parameterized type, creates a warning:
		rawBox = new Box();  // rawBox is a raw type of Box<T>
		intBox = rawBox;     // warning: unchecked conversion

		List<String> ls = (List) new ArrayList();   // warning: unchecked conversion
		
		if (intBox.getClass() == stringBox.getClass()) {
			System.out.println("intBox.getClass() == stringBox.getClass()");
		}

	}

}