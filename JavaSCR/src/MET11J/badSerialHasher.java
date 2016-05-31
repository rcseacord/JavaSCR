package MET11J;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

class badSerialHasher {
	  public static void main(String[] args)
	                     throws IOException, ClassNotFoundException {
	    Hashtable<myKey,String> ht = new Hashtable<myKey, String>();
	    myKey key = new myKey();
	    ht.put(key, "Value");
	    System.out.println("Entry: " + ht.get(key));
	    // Retrieve using the key, works
	 
	    // Serialize the Hashtable object
	    FileOutputStream fos = new FileOutputStream("hashdata.ser");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(ht);
	    oos.close();
	 
	    // Deserialize the Hashtable object
	    FileInputStream fis = new FileInputStream("hashdata.ser");
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    Hashtable<myKey, String> ht_in =
	        (Hashtable<myKey, String>)ois.readObject();
	    ois.close();
	 
	    if (ht_in.contains("Value"))
	      // Check whether the object actually exists in the hash table
	      System.out.println("Value was found in deserialized object.");
	 
	    // can fail after serialization and deserialization. Consequently, 
	    // it may be impossible to retrieve the value of the object after 
	    // deserialization by using the original key.
	    
	    if (ht_in.get(key) == null) // Gets printed
	      System.out.println(
	          "Object was not found when retrieved using the key.");
	  }
	}