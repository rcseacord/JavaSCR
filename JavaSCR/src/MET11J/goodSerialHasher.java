package MET11J;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

class goodSerialHasher {
  public static void main(String[] args)
                     throws IOException, ClassNotFoundException {
	// The type of the key value has been changed to an Integer object. 
    Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
    ht.put(new Integer(1), "Value");
    System.out.println("Entry: " + ht.get(1)); // Retrieve using the key
 
    // Serialize the Hashtable object
    FileOutputStream fos = new FileOutputStream("hashdata.ser");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(ht);
    oos.close();
 
    // Deserialize the Hashtable object
    FileInputStream fis = new FileInputStream("hashdata.ser");
    ObjectInputStream ois = new ObjectInputStream(fis);
    Hashtable<Integer, String> ht_in =
        (Hashtable<Integer, String>)(ois.readObject());
    ois.close();
 
    if (ht_in.contains("Value"))
      // Check whether the object actually exists in the Hashtable
      System.out.println("Value was found in deserialized object.");
 
    if (ht_in.get(1) == null)  // Not printed
      System.out.println(
          "Object was not found when retrieved using the key.");
  }
}