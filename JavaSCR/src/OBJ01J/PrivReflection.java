package OBJ01J;

import java.lang.reflect.Field;

public class PrivReflection {
	public static void main(String args[]) throws Exception {
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