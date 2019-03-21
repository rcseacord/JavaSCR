// The MIT License (MIT)
// 
// Copyright (c) 2018 Robert C. Seacord
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

package ids04j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SecureUnzip {
  static final int BUFFER = 512;
  static final int TOOBIG = 0x6400000; // Max size of unzipped data, 100MB
  static final int TOOMANY = 1024; // Max number of files

  private static String validateFilename(String filename, String intendedDir) throws java.io.IOException {
    File f = new File(filename);
    String canonicalPath = f.getCanonicalPath();

    File iD = new File(intendedDir);
    String canonicalID = iD.getCanonicalPath();

    if (canonicalPath.startsWith(canonicalID)) {
      return canonicalPath;
    }
    throw new IllegalStateException("File is outside extraction target directory."); 
  }

  public static void unzip(String filename) throws java.io.IOException {
    try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
      ZipEntry entry;
      int entries = 0;
      long total = 0;
      while ((entry = zis.getNextEntry()) != null) {
        System.out.println("Extracting: " + entry); 
        int count;
        byte[] data = new byte[BUFFER];
        // Write the files to the disk, but ensure that the filename is valid,
        // and that the file is not insanely big
        String name = validateFilename(entry.getName(), "."); 
        if (entry.isDirectory()) {
          System.out.println("Creating directory " + name); 
          new File(name).mkdir();
          continue;
        }
        try (BufferedOutputStream dest = new BufferedOutputStream(new FileOutputStream(name), BUFFER)) {
          while ((count = zis.read(data, 0, BUFFER)) != -1) {
            total += count;
            if (total >= TOOBIG) {
              throw new IllegalStateException("Data limit exceeded."); 
            }
            dest.write(data, 0, count);
          }
          dest.flush();
        }
        zis.closeEntry();
        entries++;
        if (entries > TOOMANY) {
          throw new IllegalStateException("File limited exceeded."); 
        }
      }  // end while more zip file entries
    } // end try-with-resources block
  } // end unzip method

  public static void main(String[] args) {
    try {
      unzip("JavaSCR/src/main/java/ids04j/10GB/10GB.zip");
    } catch (IOException e) {
      System.err.println("Could not unzip file.");
    }
  } // end main
}
