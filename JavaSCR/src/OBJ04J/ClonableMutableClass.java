package OBJ04J;

import java.util.Date;

public final class ClonableMutableClass implements Cloneable {
	  private final Date date; // final field
	  
	  private ClonableMutableClass(Date d) {
	    this.date = new Date(d.getTime());  // Copy in
	  }
	  
	  public Date getDate() {
	    return (Date)this.date.clone(); // Copy and return
	  }
	  
	  @Override
    public ClonableMutableClass clone() {
	    Date d = (Date)this.date.clone();
	    return new ClonableMutableClass(d);
	  }
	}
