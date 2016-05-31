package MET08J;

class xCard extends card {
	  private String type;
	  public xCard(int number, String type) {
	    super(number);
	    this.type = type;
	  }
	 
	  public boolean equals(Object o) {
	    if (!(o instanceof card)) {
	      return false;
	    }
	 
	    // Normal Card, do not compare type
	    if (!(o instanceof xCard)) {
	      return o.equals(this);
	    }
	 
	    // It is an xCard, compare type as well
	    xCard xc = (xCard)o;
	    return super.equals(o) && xc.type == type;
	  }
	 
		// Comply with MET09-J
		public int hashCode() {
			/* ... */
			return 0;
		}
	 
	  public static void main(String[] args) {
	    xCard p1 = new xCard(1, "type1");
	    card p2 = new card(1);
	    xCard p3 = new xCard(1, "type2");
	    
	    // p1 and p2 compare equal and p2 and p3 compare equal, 
	    // but p1 and p3 compare unequal, violating the transitivity requirement. 
	    // The problem is that the Card class has no knowledge of the XCard class 
	    // and consequently cannot determine that p2 and p3 have different values 
	    // for the field type.
	    System.out.println(p1.equals(p2)); // Returns true
	    System.out.println(p2.equals(p3)); // Returns true
	    System.out.println(p1.equals(p3)); // Returns false
	                                       // violating transitivity
	  }
	}