package ser09j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ser09j.Bicycle;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * A simple Java program to demonstrate how to perform input validation on
 * serialized binary buffers. Specifically, we only want to allow instances of
 * the Bicycle class to be deserialized.
 * 
 * @author Pierre Ernst
 * 
 */
public class LookAheadDeserializer {

  private static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(ba)) {
      oos.writeObject(o);
      return ba.toByteArray();
    }
  }

  static byte[] DoSpayload() throws IOException {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      Set<Object> t1 = new HashSet<>();
      Set<Object> t2 = new HashSet<>();
      t1.add("foo"); // make it not equal to t2 
      s1.add(t1);
      s1.add(t2);
      s2.add(t1);
      s2.add(t2);
      s1 = t1;
      s2 = t2;
    }
    return serialize(root);
  }

    
  private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
    Object obj;
    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
      // (2) TODO: enable custom filter
      // ois.setObjectInputFilter(new BikeFilter());
      obj = ois.readObject();
    }
    return obj;
  }

  public static void main(String[] args) throws ClassNotFoundException, IOException {
    
    // (3) TODO: enable process-wide filter 
    //Properties props = System.getProperties();
    //props.setProperty("jdk.serialFilter", "ser09j.Bicycle;!*;maxdepth=1;maxrefs=1;maxbytes=78;maxarray=10");
    
    byte[] serializedBicycle = null;
    byte[] serializedFile = null;
    try {
      // Serialize a Bicycle instance
      serializedBicycle = serialize(new Bicycle(0, "Unicycle", 1));
      
      // Serialize a File instance
      serializedFile = serialize(new File("file.txt")); 
    } catch (IOException e1) {
      e1.printStackTrace();
    } 

    // Deserialize the Bicycle instance (legitimate use case)
    Bicycle bicycle0 = (Bicycle) deserialize(serializedBicycle);
    System.out.println(bicycle0.getName() + " has been deserialized."); 

    try {
      // Deserialize the File instance (misuse case)
      @SuppressWarnings("unused")
      Object exploit = deserialize(serializedFile);
      System.out.println("exploit has been deserialized."); 
    } catch (ClassNotFoundException | IOException e) {
       e.printStackTrace();
    }

    // Deserialize DoS
    // (4) TODO: Wouter Coekaerts DoS Attack
    try {
       System.out.println("Attempting DoS Attack");
       deserialize(DoSpayload());
      System.out.println("DoS has been deserialized.");
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

  } // end main
} // end LookAheadDeserializer









































/*
private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException, ConfigurationException {
  Object obj; 
  try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer); 
  // Use ValidatingObjectInputStream instead of InputStream
  ValidatingObjectInputStream ois = new ValidatingObjectInputStream(bais);) {
    ois.accept(Bicycle.class); 
    obj = ois.readObject(); 
  } 
  return obj; 
}
 */