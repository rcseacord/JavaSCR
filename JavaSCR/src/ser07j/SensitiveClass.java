package ser07j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class SensitiveClass extends Number {
  private static final long serialVersionUID = 1L;
  private static final SensitiveClass INSTANCE = new SensitiveClass();

  public static SensitiveClass getInstance() {
    return INSTANCE;
  }

  private SensitiveClass() {
    // Perform security checks and parameter validation
  }

  private int balance = 1000;

  protected int getBalance() {
    return balance;
  }

  @Override
  public double doubleValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public float floatValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int intValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long longValue() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  // Serialization proxy for Period class
  private static class SerializationProxy implements Serializable {
    private static final long serialVersionUID = 1466931787417947739L;

    SerializationProxy(SensitiveClass sc) {
      // ...
    }

    // readResolve method for Period.SerializationProxy
    private Object readResolve() {
      return INSTANCE; 
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
    SensitiveClass sc = (SensitiveClass) deserialize(serialize(new SensitiveClass()));
    System.out.println(sc.toString());
  }
  
} // end class SensitiveClass
