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

class GoodSerialHasher {
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