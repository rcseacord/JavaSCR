package MET08J;

public final class caseInsensitiveStringGood {
  private String s;
 
  public caseInsensitiveStringGood(String s) {
    if (s == null) {
      throw new NullPointerException();
    }
    this.s = s;
  }
 
  // Simplified to operate only on instances of the 
  // CaseInsensitiveString class, consequently preserving symmetry
  public boolean equals(Object o) {
    return o instanceof caseInsensitiveStringGood &&
        ((caseInsensitiveStringGood)o).s.equalsIgnoreCase(s);
  }
 
	// Comply with MET09-J
	public int hashCode() {
		/* ... */
		return 0;
	}
 
  public static void main(String[] args) {
    caseInsensitiveStringGood cis = new caseInsensitiveStringGood("Java");
    String s = "java";
    System.out.println(cis.equals(s)); // Returns false now
    System.out.println(s.equals(cis)); // Returns false now
  }
}