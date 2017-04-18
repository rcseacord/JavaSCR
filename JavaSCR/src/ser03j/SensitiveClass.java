package ser03j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

public class SensitiveClass extends Number {
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
private void writeObject(java.io.ObjectOutputStream out) throws IOException {
  throw new IOException("Not serializable");
}
private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
  throw new IOException("Not serializable");
}
*/
