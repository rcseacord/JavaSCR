package ser09j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeEnumSingleton {
  public static void main(String[] args) throws ClassNotFoundException, IOException {
    EnumSingleton one;
    EnumSingleton.INSTANCE.setValue(21);
    System.out.println("EnumSingleton.INSTANCE = " + EnumSingleton.INSTANCE.getValue());
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempdata.ser"))) {
      one = (EnumSingleton) ois.readObject();
      System.out.println("one = " + one.getValue());
    }

    EnumSingleton.INSTANCE.setValue(21);
    System.out.println("EnumSingleton.INSTANCE = " + EnumSingleton.INSTANCE.getValue());
    System.out.println("one = " + one.getValue());
  }
}
