// The MIT License (MIT)
//
// Copyright (c) 2022 Robert C. Seacord
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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public final class Hometown implements Serializable {
  private static final long serialVersionUID = 6515419803685137985L;
  // Private internal state
  private String town;
  private static final String MOSCOW = "Moscow";

  private static void validateInput(String newtown) throws AccessDeniedException {
    if (MOSCOW.equals(newtown)) {
      throw new AccessDeniedException("Nyet, comrade");
    }
  }

  public Hometown(String town) throws AccessDeniedException {
    validateInput(town);
    // Initialize town to default value
    this.town = town;
  }

  // Allows callers to retrieve internal state without security check
  String getTown() {
    return this.town;
  }

  // Privileged callers can modify (private) state
  public void setTown(String newTown) throws AccessDeniedException {
    if (!this.town.equals(newTown)) {
      validateInput(newTown);
      this.town = newTown;
    }
  }

  @SuppressWarnings("static-method")
  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    String readTown;
    ObjectInputStream.GetField fields = in.readFields();
    readTown = (String) fields.get("town", null);
    this.town = readTown;
  }

  // tamper with the serialized data to change home town to Moscow
  public static void makeWarsawMoscow(String fileName) throws IOException {
    RandomAccessFile hometownFile = new RandomAccessFile(fileName, "rw");
    byte[] buffer1 = new byte[100];
    byte[] buffer2 = new byte[4];
    byte[] moscowBytes = "Moscow".getBytes(StandardCharsets.US_ASCII);

    // home town name starts 68 bytes in
    hometownFile.readFully(buffer1, 0, 67);

    // skip over "Warsaw" in file
    if (moscowBytes.length != hometownFile.skipBytes(moscowBytes.length)) {
      throw new RuntimeException("Failed to modify hometown file.");
    }

    // read the rest of the file
    int endLength = hometownFile.read(buffer2);

    hometownFile.close();

    // copy moscow to serialization data
    System.arraycopy(moscowBytes, 0, buffer1, 67, moscowBytes.length);

    // copy the end of the serialized data
    System.arraycopy(buffer2, 0, buffer1, 67+moscowBytes.length, endLength);

    // delete the original serialized file
    // new File(fileName).delete();

    try {
      Files.delete(Paths.get(fileName));
    } catch (NoSuchFileException x) {
      System.err.format("%s: no such" + " file or directory%n", fileName);
    } catch (DirectoryNotEmptyException x) {
      System.err.format("%s not empty%n", fileName);
    } catch (IOException x) {
      // File permission problems are caught here.
      System.err.format("File permission error deleting %s%n", fileName);
    }

    // create a new serialized file with our modified hometown
    FileOutputStream serialOS = new FileOutputStream(fileName);
    serialOS.write(buffer1);
    serialOS.close();
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    // Create Hometown object
    Hometown ht = new Hometown("Warsaw");
    System.out.println("My home town is " + ht.getTown());
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempdata.ser"))
    ) {
      oos.writeObject(ht);
    }

    makeWarsawMoscow("tempdata.ser");

    // Construct a new object through deserialization
    try (
        // Edit tempdata.ser in %userprofile%\git\JavaSCR
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempdata.ser"))
    ) {
      ht = (Hometown) ois.readObject();
    }
    System.out.println("My home town is " + ht.getTown());

    // Clean up the file
    if (!new File("tempdata.ser").delete()) {
      System.err.println("Failed to delete tempdata.ser");
    }
  }
}































//private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//  String readTown;
//  ObjectInputStream.GetField fields = in.readFields();
//  readTown = (String) fields.get("town", null); 
//  // Validate town field
//  validateInput(readTown);
//  this.town = readTown;
//}