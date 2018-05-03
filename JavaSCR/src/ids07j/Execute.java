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
    // See what sort of arguments are passed
    System.out.println("------------- rt.exec PrintArgs Path with Spaces");
    String PrintArgs = "C:\\Users\\rseacord\\source\\repos\\PrintArgs\\Release\\PrintArgs.exe ";
    Runtime rt = Runtime.getRuntime();
    // PrintArgs.exe argument1 "argument 2"  "\some\path with\spaces"
    Process proc = rt.exec(PrintArgs + "argument1 \"argument 2\"  \"\\some\\path with\\spaces\"");
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

    System.out.println("------------- rt.exec PrintArgs Nested Quotes");
    rt = Runtime.getRuntime();
    proc = rt.exec(PrintArgs + "argument1 \"she said, \"you had me at hello\"\"  \"\\some\\path with\\spaces\"");
    result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() :
        proc.getErrorStream();
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    System.out.println("------------- rt.exec PrintArgs Unbalanced Quotes");
    rt = Runtime.getRuntime();
    proc = rt.exec(PrintArgs + "argument1 \"argument\"2\" argument3 argument4");
    result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() :
        proc.getErrorStream();
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    System.out.println("------------- rt.exec PrintArgs trailing backslash");
    rt = Runtime.getRuntime();
    proc = rt.exec(PrintArgs + "\"\\some\\directory with\\spaces\\\" argument2");
    result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() :
        proc.getErrorStream();
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    // Use ProcessBuilder and separate commands and each argument
    System.out.println("------------- ProcessBuilder PrintArgs Unbalanced Quotes");
    String[] PrintArgsCommand = {PrintArgs, "argument1 \"argument\"2\" argument3 argument4"};
    ProcessBuilder pb = new ProcessBuilder(PrintArgsCommand);
    proc = pb.start();
    result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    System.out.println("------------- rt.exec");
    // String dir = System.getProperty("dir");
    String dir = "&calc";
    rt = Runtime.getRuntime();
    proc = rt.exec("cmd.exe /C dir " + dir);
    result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() :
        proc.getErrorStream();
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    // Using ProcessBuilder incorrectly
    System.out.println("------------- Using ProcessBuilder incorrectly");
    pb = new ProcessBuilder("C:\\Windows\\System32\\cmd.exe /C dir " + dir);
    try {
      proc = pb.start();
      result = proc.waitFor();
      if (result != 0) {
        System.out.println("process error: " + result);
      }
      in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
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
    pb = new ProcessBuilder(command);
    proc = pb.start();
    result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
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
