package err12j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

public final class PartOne implements Cloneable {
  private static final CopyOption[] options = { StandardCopyOption.COPY_ATTRIBUTES };
  private static final Charset charset = Charset.forName("US-ASCII");
  private static final String s = "File ID";
  private final Path filepath;
  private BufferedWriter writer;

  public PartOne(Path pathname) throws IOException {
    filepath = pathname;
    writer = Files.newBufferedWriter(filepath, charset);
    writer.write(s, 0, s.length());
    writer.flush();
  }

  public void setNow(Calendar rightNow) throws IOException {
    writer.write(rightNow.toString());
    writer.flush();
  }

  // copy constructor
  public PartOne(PartOne p1) throws IOException {
    filepath = Paths.get(p1.filepath + "copy");
    Files.copy(p1.filepath, filepath, options);
    writer = Files.newBufferedWriter(filepath, charset);
  }

  public void close() throws IOException {
    try {
      writer.close();
    } finally {
      writer = null;
      Files.delete(filepath);
    }
  }
}
