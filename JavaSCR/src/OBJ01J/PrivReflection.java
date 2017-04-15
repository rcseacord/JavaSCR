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

package OBJ01J;

import java.lang.reflect.Field;

public class PrivReflection {
	public static void main(String args[]) throws Exception {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      System.out.println("Security manager installed.");
    }
    else {
      System.out.println("No security manager.");
    }
	  
		// Returns an array of Field objects reflecting 
		// all the fields declared by the class (including private)
		final Field fields[] = FieldTest.class.getDeclaredFields();

		// Enumerate fields
		for (int i = 0; i < fields.length; ++i) {
			System.out.println("Field: " + fields[i]);
		}

		for (int i = 0; i < fields.length; ++i) {
			if ("barPrivStr".equals(fields[i].getName())) {
				FieldTest fieldTest = new FieldTest();
				Field f = fields[i];

				try {
					// Get field
					System.out.println(f.get(fieldTest));
				} catch (java.lang.IllegalAccessException iae) {
					System.err.println("java.lang.IllegalAccessException" + iae.toString());
				}
				// Make private field accessible
				// Policy file: C:/Users/rseacord/.java.policy
				f.setAccessible(true);
				// Get field
				System.out.println(f.get(fieldTest));

				// Set field
				f.set(fieldTest, "fighters");
				System.out.println(f.get(fieldTest));
				break;
			}
		}
	}
}