package MET08J;

// This noncompliant code example defines a CaseInsensitiveString class that 
// includes a String and overrides the equals() method. The CaseInsensitiveString 
// class knows about ordinary strings, but the String class has no knowledge of 
// case-insensitive strings. Consequently, the CaseInsensitiveString.equals() method 
// should not attempt to interoperate with objects of the String class.

public final class caseInsensitiveString {
	private String s;

	public caseInsensitiveString(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		this.s = s;
	}

	// This method violates symmetry
	public boolean equals(Object o) {
		if (o instanceof caseInsensitiveString) {
			return s.equalsIgnoreCase(((caseInsensitiveString)o).s);
		}

		if (o instanceof String) {
			return s.equalsIgnoreCase((String)o);
		}
		return false;
	}

	// Comply with MET09-J
	public int hashCode() {
		/* ... */
		return 0;
	}

	public static void main(String[] args) {
		caseInsensitiveString cis = new caseInsensitiveString("Java");
		String s = "java";
		// By operating on String objects, the CaseInsensitiveString.equals() method 
		// violates the second contract requirement (symmetry). Because of the asymmetry, 
		// given a String object s and a CaseInsensitiveString object cis that differ 
		// only in case, cis.equals(s)) returns true, while s.equals(cis) returns false.
		System.out.println(cis.equals(s)); // Returns true
		System.out.println(s.equals(cis)); // Returns false
	}
}