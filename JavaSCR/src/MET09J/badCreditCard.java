package MET09J;

import java.util.HashMap;
import java.util.Map;

public final class badCreditCard {
	  private final int number;
	 
	  public badCreditCard(int number) {
	    this.number = number;
	  }
	 
	  public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (!(o instanceof badCreditCard)) {
	      return false;
	    }
	    badCreditCard cc = (badCreditCard)o;
	    return cc.number == number;
	  }
	 
	  public static void main(String[] args) {
	    Map<badCreditCard, String> m = new HashMap<badCreditCard, String>();
	    m.put(new badCreditCard(100), "4111111111111111");
	    // The expected retrieved value is 4111111111111111; 
	    // the actual retrieved value is null.
	    System.out.println(m.get(new badCreditCard(100))); 
	  }
	}