package MET09J;

import java.util.HashMap;
import java.util.Map;

public final class goodCreditCard {
	  private final int number;
	   
	  public goodCreditCard(int number) {
	    this.number = number;
	  }
	 
	  public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (!(o instanceof goodCreditCard)) {
	      return false;
	    }
	    goodCreditCard cc = (goodCreditCard)o;
	    return cc.number == number;
	  }
	 
	  // override the hashCode() method so that it generates the same 
	  // value for any two instances that are considered to be equal
	  // by the equals() method. 
	  public int hashCode() {
	    int result = 17;
	    result = 31 * result + number;
	    return result;
	  }
	 
	  public static void main(String[] args) {
	    Map<goodCreditCard, String> m = new HashMap<goodCreditCard, String>();
	    m.put(new goodCreditCard(100), "4111111111111111");
	    System.out.println(m.get(new goodCreditCard(100)));
	  }
	}