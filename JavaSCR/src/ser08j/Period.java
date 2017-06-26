package ser08j;

import java.io.Serializable;
import java.util.Date;

public final class Period implements Serializable {
  private static final long serialVersionUID = 4933289900620142386L;
  private Date start;
  private Date end;

  /**
   * @param start
   *          the beginning of the period
   * @param end
   *          the end of the period; must not precede start
   * @throws IllegalArgumentException
   *           if start is after end
   * @throws NullPointerException
   *           if start or end is null
   */
  public Period(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());
    if (this.start.compareTo(this.end) > 0)
      throw new IllegalArgumentException(start + " after " + end); //$NON-NLS-1$
  }

  public Date start() {
    return new Date(this.start.getTime());
  }

  public Date end() {
    return new Date(this.end.getTime());
  }

  @Override
  public String toString() {
    return this.start + " - " + this.end; //$NON-NLS-1$
  }

  // readObject method with validity checking - Page 304
  // This will defend against BogusPeriod attack but not MutablePeriod.
  // private void readObject(ObjectInputStream s)
  // throws IOException, ClassNotFoundException {
  // s.defaultReadObject();
  //
  // // Check that our invariants are satisfied
  // if (start.compareTo(end) > 0)
  // throw new InvalidObjectException(start +" after "+ end);
  // }

  // readObject method with defensive copying and validity checking - Page 306
  // This will defend against BogusPeriod and MutablePeriod attacks.
  // private void readObject(ObjectInputStream s)
  // throws IOException, ClassNotFoundException {
  // s.defaultReadObject();
  //
  // // Defensively copy our mutable components
  // start = new Date(start.getTime());
  // end = new Date(end.getTime());
  //
  // // Check that our invariants are satisfied
  // if (start.compareTo(end) > 0)
  // throw new InvalidObjectException(start +" after "+ end);
  // }
}