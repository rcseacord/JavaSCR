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
import java.util.Arrays;
import java.util.Objects;

public class Execute {

  static void getOutput(Process proc) throws IOException, InterruptedException {
    int result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    InputStream in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
    int c;
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }
  } // end get Output

  public static void main(String[] args) throws Exception {
    Runtime rt = Runtime.getRuntime();
    String nl = System.getProperty("line.separator");  // retrieve line separator dependent on OS.
    // See what sort of arguments are passed
    System.out.println("[rt.exec] PrintArgs Path with Spaces");
    String PrintArgs = "C:\\Users\\rseacord\\source\\repos\\PrintArgs\\Release\\PrintArgs.exe ";
    System.out.println("PrintArgs.exe argument1 \"argument 2\"  \"\\some\\path with\\spaces\"");
    Process proc = rt.exec(PrintArgs + "argument1 \"argument 2\"  \"\\some\\path with\\spaces\"");
    getOutput(proc);

    System.out.println(nl + "+-----------------------------------------------------+" );

    System.out.println(nl + "[rt.exec] PrintArgs Nested Quotes");
    rt = Runtime.getRuntime();
    System.out.println("PrintArgs argument1 \"she said, \"you had me at hello\"\"  \"\\some\\path with\\spaces\"");
    proc = rt.exec(PrintArgs + "argument1 \"she said, \"you had me at hello\"\"  \"\\some\\path with\\spaces\"");
    getOutput(proc);

    System.out.println(nl + "+-----------------------------------------------------+" );

    System.out.println(nl + "[rt.exec] PrintArgs Unbalanced Quotes");
    rt = Runtime.getRuntime();
    System.out.println("PrintArgs argument1 \"argument\"2\" argument3 argument4");
    proc = rt.exec(PrintArgs + "argument1 \"argument\"2\" argument3 argument4");
    getOutput(proc);

    System.out.println(nl + "+-----------------------------------------------------+" );

    System.out.println(nl + "[rt.exec] PrintArgs trailing backslash");
    rt = Runtime.getRuntime();
    System.out.println("PrintArgs \"\\some\\directory with\\spaces\\\" argument2");
    proc = rt.exec(PrintArgs + "\"\\some\\directory with\\spaces\\\" argument2");
    getOutput(proc);

    System.out.println(nl + "+-----------------------------------------------------+" );

    // Use ProcessBuilder and separate commands and each argument
    System.out.println(nl + "[ProcessBuilder] PrintArgs Unbalanced Quotes");
    String[] PrintArgsCommand = {PrintArgs, "argument1 \"argument\"2\" argument3 argument4"};
    System.out.println("PrintArgs argument1 \"argument\"2\" argument3 argument4");
    ProcessBuilder pb = new ProcessBuilder(PrintArgsCommand);
    proc = pb.start();
    getOutput(proc);

    System.out.println(nl + "+-----------------------------------------------------+" );

    System.out.println(nl + "[rt.exec] command shell with &calc argument");
    System.out.println("cmd.exe /C dir &calc");
    String dir = "&calc";  // substituted for System.getProperty("dir");
    rt = Runtime.getRuntime();
    System.out.println("cmd.exe /C dir " + dir);
    proc = rt.exec("cmd.exe /C dir " + dir);
    int result = proc.waitFor();
    if (result != 0) {
      System.out.println("process error: " + result);
    }
    InputStream in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
    int c;
    while ((c = in.read()) != -1) {
      System.out.print((char) c);
    }

    System.out.println(nl + "+-----------------------------------------------------+" );

    // Using ProcessBuilder incorrectly (without separating arguments)
    System.out.println(nl + "[ProcessBuilder] used incorrectly (without separating arguments)");
    System.out.println("C:\\Windows\\System32\\cmd.exe /C dir " + dir);
    pb = new ProcessBuilder("C:\\Windows\\System32\\cmd.exe /C dir " + dir);
    try {
      proc = pb.start();
      getOutput(proc);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    System.out.println(nl + "+-----------------------------------------------------+" );

    // Use ProcessBuilder and separate commands and each argument
    System.out.println(nl + "[ProcessBuilder] separating commands and each argument");
    String[] command = {"C:\\Windows\\System32\\cmd.exe", "/C", "dir", dir};
    System.out.println(Arrays.toString(command) + dir);
    pb = new ProcessBuilder(command);
    proc = pb.start();
    getOutput(proc);

    System.out.println(nl + "+-----------------------------------------------------+" );

    // Secure method avoiding Runtime.exec()
    System.out.println(nl + "[Library function] dir &calc");
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
