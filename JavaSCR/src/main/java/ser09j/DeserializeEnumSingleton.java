package ser09j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeEnumSingleton {
  public static void main(String[] args) throws ClassNotFoundException, IOException {
    EnumSingleton.INSTANCE.setValue(21);
    System.out.println("EnumSingleton.INSTANCE = " + EnumSingleton.INSTANCE.getValue());
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempdata.ser"))) {
      EnumSingleton one = (EnumSingleton) ois.readObject();
      System.out.println("EnumSingleton.INSTANCE == one is " + (EnumSingleton.INSTANCE == one));
      System.out.println("one = " + one.getValue());
      System.out.println("EnumSingleton.INSTANCE = " + EnumSingleton.INSTANCE.getValue());
     }
  }
}
