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
    throw new IllegalStateException("File is outside extraction target directory."); //$NON-NLS-1$
  }

  public final static void unzip(String filename) throws java.io.IOException {
    try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(filename)));) {
      ZipEntry entry;
      int entries = 0;
      long total = 0;
      while ((entry = zis.getNextEntry()) != null) {
        System.out.println("Extracting: " + entry); //$NON-NLS-1$
        int count;
        byte data[] = new byte[BUFFER];
        // Write the files to the disk, but ensure that the filename is valid,
        // and that the file is not insanely big
        String name = validateFilename(entry.getName(), "."); //$NON-NLS-1$
        if (entry.isDirectory()) {
          System.out.println("Creating directory " + name); //$NON-NLS-1$
          new File(name).mkdir();
          continue;
        }
        try (BufferedOutputStream dest = new BufferedOutputStream(new FileOutputStream(name), BUFFER);) {
          while (total + BUFFER <= TOOBIG && (count = zis.read(data, 0, BUFFER)) != -1) {
            dest.write(data, 0, count);
            total += count;
          }
          dest.flush();
        }
        zis.closeEntry();
        entries++;
        if (entries > TOOMANY) {
          throw new IllegalStateException("Too many files to unzip."); //$NON-NLS-1$
        }
        if (total > TOOBIG) {
          throw new IllegalStateException("File being unzipped is too big."); //$NON-NLS-1$
        }
      }
    }
  }

  public static void main(String[] args) {
    try {
      unzip("C:/Users/rseacord/git/JavaSCR/JavaSCR/src/ids04j/10GB/10GB.zip"); //$NON-NLS-1$
    } catch (IOException e) {
      System.err.println(e);
    }
  } // end main
}
