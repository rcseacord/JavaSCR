package OBJ04J;

import java.util.Date;

public final class ClonableMutableClass implements Cloneable {
	  private final Date date; // final field
	  
	  public ClonableMutableClass(Date d) {
	    this.date = new Date(d.getTime());  // Copy in
	  }
	  
	  public Date getDate() {
	    return (Date)date.clone(); // Copy and return
	  }
	  
	  public ClonableMutableClass clone() {
	    Date d = (Date)date.clone();
	    return new ClonableMutableClass(d);
	  }
	}
