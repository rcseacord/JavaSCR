// The MIT License (MIT)
// 
// Copyright (c) 2016 Robert C. Seacord
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

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