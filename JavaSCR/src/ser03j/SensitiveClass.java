package ser03j;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;

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
  
} // end class SensitiveClass







































/*
 // Cause deserialization to fail
  private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    throw new IOException("Not serializable");
  }
  
  private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
    throw new IOException("Not serializable");
  }
  
  @SuppressWarnings("unused")
  private void readObjectNoData() throws ObjectStreamException {
     throw new InvalidObjectException("Not serializable");
  }
  
 */
