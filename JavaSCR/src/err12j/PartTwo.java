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

public final class PartTwo implements AutoCloseable {
  private static final CopyOption[] options = { StandardCopyOption.COPY_ATTRIBUTES };
  private static final Charset charset = Charset.forName("US-ASCII");
  private static final String s = "File ID";
  private final Path filepath;
  private BufferedWriter writer;

  public PartTwo(Path pathname) throws IOException {
    filepath = pathname;
    writer = Files.newBufferedWriter(filepath, charset);
    writer.write(s, 0, s.length());
    writer.flush();
  }
  
  // copy constructor
  public PartTwo(PartTwo p2) throws IOException {
    filepath = Paths.get(p2.filepath + "copy");
    Files.copy(p2.filepath, filepath, options);
    writer = Files.newBufferedWriter(filepath, charset);
  }

  public void setNow(Calendar rightNow) throws IOException {
    writer.write(rightNow.toString());  
    writer.flush();
    // throw new IOException("injected fault");
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
