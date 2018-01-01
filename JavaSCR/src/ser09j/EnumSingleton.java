package ser09j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public enum EnumSingleton {
  INSTANCE;
  int value;
  public int getValue() {
    return value;
  }
  public void setValue(int value) {
    this.value = value;
  }

  static void serialize(Object o) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempdata.ser"))) {
      oos.writeObject(o);
    }
  }

  public static void main(String[] args) throws IOException {
    EnumSingleton.INSTANCE.setValue(42);
    System.out.println("EnumSingleton.INSTANCE = " + EnumSingleton.INSTANCE.getValue());
    serialize(EnumSingleton.INSTANCE);
  }
}
