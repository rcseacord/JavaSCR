// The MIT License (MIT)
//
// Copyright (c) 2017 Robert C. Seacord
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

package ser04j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.AccessDeniedException;

public final class Hometown implements Serializable {
  private static final long serialVersionUID = 6515419803685137985L;
  // Private internal state
  private String town;
  private static final String UNKNOWN = "UNKNOWN";
  private static final String MOSCOW = "Moscow";

  void performSecurityManagerCheck(String town) throws AccessDeniedException {
    if (MOSCOW.equals(town)) {
      throw new AccessDeniedException("Nyet, comrade");
    }
  }

  void validateInput(String newCC) throws IllegalArgumentException {
    // ...
  }

  public Hometown(String town) throws AccessDeniedException {
    performSecurityManagerCheck(town);
    validateInput(town);
    // Initialize town to default value
    this.town = town;
  }

  // Allows callers to retrieve internal state without security check
  String getTown() {
    return town;
  }

  // Privileged callers can modify (private) state
  public void setTown(String newTown) throws AccessDeniedException {
    if (!town.equals(newTown)) {
      performSecurityManagerCheck(newTown);
      validateInput(newTown);
      town = newTown;
    }
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    // out.writeObject(town);
    out.defaultWriteObject();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    String town;
    ObjectInputStream.GetField fields = in.readFields();
    town = (String) fields.get("town", null);
    // If the deserialized name does not match the default value normally
    // created at construction time, duplicate the checks
    if (!UNKNOWN.equals(town)) {
      performSecurityManagerCheck(town); 
      validateInput(town); 
    }

    this.town = town;
  }

  public static void main(String[] args) throws AccessDeniedException {
    Hometown myTown = new Hometown("Pittsburgh");
    System.out.println("My town is " + myTown.getTown());

    // Create object that violates security checks
    try {
      Hometown ht = new Hometown("Warsaw");

      FileOutputStream fos = new FileOutputStream("tempdata.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(ht);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace(System.err);
    }

    // Construct a new object through deserialization
    try {
      // NOTE: File is in %userprofile%\git\JavaSCR\JavaSCR
      FileInputStream fis = new FileInputStream("tempdata.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      Hometown ht = (Hometown) ois.readObject();
      ois.close();
      System.out.println("My town is " + ht.getTown());

      // Clean up the file
      new File("tempdata.ser").delete();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace(System.err);
    }
  }
}






































/*
 * add performSecurityManagerCheck at line 94 if (!UNKNOWN.equals(town)) {
 * performSecurityManagerCheck(town); validateInput(town); }
 */
