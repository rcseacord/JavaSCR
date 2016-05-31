package MET08J;


class xCardGood {
  private String type;
  
  // Composition: private card field added
  private card card; 
 
  public xCardGood(int number, String type) {
    card = new card(number);
    this.type = type;
  }
 
  // new viewCard() method added
  public card viewCard() {
    return card;
  }
 
  public boolean equals(Object o) {
    if (!(o instanceof xCardGood)) {
      return false;
    }
 
    xCardGood cp = (xCardGood)o;
    return cp.card.equals(card) && cp.type.equals(type);
  }
 
	// Comply with MET09-J
	public int hashCode() {
		/* ... */
		return 0;
	}
 
  public static void main(String[] args) {
    xCardGood p1 = new xCardGood(1, "type1");
    card p2 = new card(1);
    xCardGood p3 = new xCardGood(1, "type2");
    xCardGood p4 = new xCardGood(1, "type1");
    System.out.println(p1.equals(p2)); // Prints false
    System.out.println(p2.equals(p3)); // Prints false
    System.out.println(p1.equals(p3)); // Prints false
    System.out.println(p1.equals(p4)); // Prints true
  }
}