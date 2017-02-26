package err12j;

import java.util.Date;

public final class PartTwo implements Cloneable {
	private final Date date; // final field
  private Whole parent;

	public PartTwo(Date d) {
		this.date = new Date(d.getTime()); // Copy in
	}

	public Date getDate() {
		return (Date) date.clone(); // Copy and return
	}

  public void setParent(Whole Source) {
    this.parent = Source;
  }
	
	public PartTwo clone() {
		Date d = (Date) date.clone();
		return new PartTwo(d);
	}
	
  public void dispose() {
    // TODO
  }
	
}
