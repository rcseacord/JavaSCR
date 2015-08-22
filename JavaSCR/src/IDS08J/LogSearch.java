// The MIT License (MIT)
// 
// Copyright (c) 2015 Secure Coding Institute
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

package IDS08J;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogSearch {

  public static void FindLogEntryBad(String search) {
    // Construct regex dynamically from user string
    String regex = "(.*? +public\\[\\d+\\] +.*" + search + ".*)";
    Pattern searchPattern = Pattern.compile(regex);
    try (FileInputStream fis = new FileInputStream("src/IDS08J/log.txt")) {
      FileChannel channel = fis.getChannel();
      // Get the file's size and map it into memory
      long size = channel.size();
      final MappedByteBuffer mappedBuffer = channel.map(
          FileChannel.MapMode.READ_ONLY, 0, size);

      Charset charset = Charset.forName("ISO-8859-15");
      final CharsetDecoder decoder = charset.newDecoder();
      // Read file into char buffer
      CharBuffer log = decoder.decode(mappedBuffer);
      Matcher logMatcher = searchPattern.matcher(log);
      while (logMatcher.find()) {
        String match = logMatcher.group();
        if (!match.isEmpty()) {
          System.out.println(match);
        }
      }
    } catch (IOException ex) {
      System.err.println("thrown exception: " + ex.toString());
      Throwable[] suppressed = ex.getSuppressed();
      for (int i = 0; i < suppressed.length; i++) {
        System.err.println("suppressed exception: " + suppressed[i].toString());
      }
    }
    return;
  }

  public static void FindLogEntry(String search) {
    // Sanitize search string
    StringBuilder sb = new StringBuilder(search.length());
    for (int i = 0; i < search.length(); ++i) {
      char ch = search.charAt(i);
      if (Character.isLetterOrDigit(ch) || ch == ' ' || ch == '\'') {
        sb.append(ch);
      }
    }
    search = sb.toString();

    // Construct regex dynamically from user string
    String regex = "(.*? +public\\[\\d+\\] +.*" + search + ".*)";
    Pattern searchPattern = Pattern.compile(regex);

    // Open log file and search using search pattern
    try (FileInputStream fis = new FileInputStream("src/IDS08J/log.txt")) {
      FileChannel channel = fis.getChannel();
      // Get the file's size and map it into memory
      long size = channel.size();
      final MappedByteBuffer mappedBuffer = channel.map(
          FileChannel.MapMode.READ_ONLY, 0, size);

      Charset charset = Charset.forName("ISO-8859-15");
      final CharsetDecoder decoder = charset.newDecoder();
      // Read file into char buffer
      CharBuffer log = decoder.decode(mappedBuffer);
      Matcher logMatcher = searchPattern.matcher(log);
      while (logMatcher.find()) {
        String match = logMatcher.group();
        if (!match.isEmpty()) {
          System.out.println(match);
        }
      }
    } catch (IOException ex) {
      System.err.println("thrown exception: " + ex.toString());
      Throwable[] suppressed = ex.getSuppressed();
      for (int i = 0; i < suppressed.length; i++) {
        System.err.println("suppressed exception: " + suppressed[i].toString());
      }
    }
    return;
  }

  public static void main(String[] args) {
    FindLogEntryBad(args[0]);
    System.out.println("++++++++++++++++++++++");
    FindLogEntry(args[0]);
  }

}
