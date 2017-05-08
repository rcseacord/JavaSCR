package ser07j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public final class Period implements Serializable {
  private static final long serialVersionUID = 1639369965306260328L;
  private final Date start;
  private final Date end;

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
      throw new IllegalArgumentException(start + " after " + end);
  }

  public Date start() {
    return new Date(start.getTime());
  }

  public Date end() {
    return new Date(end.getTime());
  }

  public String toString() {
    return start + " - " + end;
  }

  // Serialization proxy for Period class
  private static class SerializationProxy implements Serializable {
    private final Date start;
    private final Date end;

    SerializationProxy(Period p) {
      this.start = p.start;
      this.end = p.end;
    }

    private static final long serialVersionUID = 234098243823485285L;

    // readResolve method for Period.SerializationProxy
    private Object readResolve() {
      return new Period(start, end); // Uses public constructor
    }
  }

  // writeReplace method for the serialization proxy pattern
  // This method can be copied verbatim into any class with a serialization
  // proxy:
  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  // readObject method for the serialization proxy pattern
  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("Proxy required");
  }

  // The rest of this is to serialize/deserialize
  static Object deserialize(byte[] bytes) throws Exception {
    return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
  }

  static byte[] serialize(Object o) throws IOException {
    ByteArrayOutputStream ba = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(ba);
    oos.writeObject(o);
    oos.close();
    return ba.toByteArray();
  }

  public static void main(String[] args) throws Exception {
    Period period = (Period) deserialize(serialize(new Period(new Date(), new Date())));
    System.out.println(period.toString());
  }
}
