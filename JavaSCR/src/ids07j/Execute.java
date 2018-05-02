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

package ids07j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Execute {
  public static void main(String[] args) throws Exception {
    System.out.println("------------- rt.exec");
    // String dir = System.getProperty("dir");
    String dir = "&calc";
    Runtime rt = Runtime.getRuntime();
    Process proc = rt.exec("cmd.exe /C dir " + dir);
    int result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    InputStream in = (result == 0) ? proc.getInputStream() :
        proc.getErrorStream();
    int c;
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    // Using ProcessBuilder incorrectly
    System.out.println("------------- Using ProcessBuilder incorrectly");
    ProcessBuilder b = new ProcessBuilder("C:\\Windows\\System32\\cmd.exe /C dir " + dir);
    try {
      Process p1 = b.start();
      result = p1.waitFor();
      if (result != 0) {
        System.out.println("process error: " + result);
      }
      in = (result == 0) ? proc.getInputStream() :
          proc.getErrorStream();
      while ((c = in.read()) != -1) {
        System.out.print((char) c);
      }
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }

    // Use ProcessBuilder and separate commands and each argument
    System.out.println("------------- Using ProcessBuilder correctly");
    String[] command = {"C:\\Windows\\System32\\cmd.exe", "/C", "dir", dir};
    ProcessBuilder pb = new ProcessBuilder(command);
    Process p2 = pb.start();
    result = p2.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() :
        proc.getErrorStream();
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    // Secure method avoiding Runtime.exec()
    System.out.println("------------- Library function");
    // File pathname = new File(System.getProperty("dir"));
    File pathname = new File(dir);
    if (pathname.isDirectory()) {
      for (String file : Objects.requireNonNull(pathname.list())) {
        System.out.println(file);
      }
    } else {
      System.out.println("Not a directory");
    }
  }
}
