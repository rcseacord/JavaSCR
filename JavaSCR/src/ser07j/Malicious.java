package ser07j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Malicious {
  
  // This method should not be used in production code
  static public Object deepCopy(Object obj) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      new ObjectOutputStream(bos).writeObject(obj);
      ByteArrayInputStream bin =
          new ByteArrayInputStream(bos.toByteArray());
      return new ObjectInputStream(bin).readObject();
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  } // end deepCopy()
  
  public static void main(String[] args) {
    SensitiveClass sc =
       (SensitiveClass) deepCopy(SensitiveClass.getInstance());
    // Prints false; indicates new instance
    System.out.println(sc == SensitiveClass.getInstance()); 
    System.out.println("Balance = " + sc.getBalance());
  }
 
}