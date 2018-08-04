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
    return new Date(this.start.getTime());
  }

  public Date end() {
    return new Date(this.end.getTime());
  }

  @Override
  public String toString() {
    return this.start + " - " + this.end; 
  }

  // Serialization proxy for Period class
  private static class SerializationProxy implements Serializable {
    private static final long serialVersionUID = 234098243823485285L;
    private final Date start;
    private final Date end;

    SerializationProxy(Period p) {
      // copy argument data without consistency checking or defensive copying
      this.start = p.start;
      this.end = p.end;
    }
    
    // readResolve method for Period.SerializationProxy
    private Object readResolve() {
      return new Period(this.start, this.end); // Uses public constructor
    }
  }

  // writeReplace method for the serialization proxy pattern
  // Method can be copied verbatim into any class with a serialization proxy.
  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  // readObject method for the serialization proxy pattern
  private void readObject(@SuppressWarnings("unused") ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("Proxy required"); 
  }

  // The rest of this is to serialize/deserialize
  private static Object deserialize(byte[] bytes) throws Exception {
    return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
  }

  private static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(ba)) {
      oos.writeObject(o);
      return ba.toByteArray();
    }
  }

  public static void main(String[] args) throws Exception {
    Period period = (Period) deserialize(serialize(new Period(new Date(), new Date())));
    System.out.println(period.toString());
  }
}
