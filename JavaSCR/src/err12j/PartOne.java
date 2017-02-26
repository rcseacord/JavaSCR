package err12j;

import java.util.Date;

public final class PartOne implements Cloneable {
  private final Date date;
  private Whole parent;

  public PartOne(Date d) {
    this.date = new Date(d.getTime()); // Copy in
  }

  public Date getDate() {
    return (Date) date.clone(); // Copy and return
  }

  public void setParent(Whole Source) {
    this.parent = Source;
  }
  
  public PartOne clone() {
    Date d = (Date) date.clone();
    return new PartOne(d);
  }
  
  public void dispose() {
    // TODO
  }
}
