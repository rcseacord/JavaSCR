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

package err12j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

class Whole {

  private PartOne p1;
  private PartTwo p2;

  private Whole(Path path1, Path path2) throws IOException {
    this.p1 = new PartOne(path1);
    this.p2 = new PartTwo(path2);
  }

  private Whole(PartOne pone, PartTwo ptwo) {
    this.p1 = pone;
    this.p2 = ptwo;
  }

  private PartOne getp1() {
    return this.p1;
  }

  private PartTwo getp2() {
    return this.p2;
  }

  private Whole move(Whole Source) throws IOException {
    PartOne t1 = new PartOne(Source.getp1());
    //noinspection ConstantConditions
    if (t1 != null) { // not necessary for copy constructor
      try {
        PartTwo t2 = new PartTwo(Source.getp2());
        //noinspection ConstantConditions
        if (t2 != null) { // not necessary for copy constructor
          try {
            // methods that might throw
            Calendar rightNow = Calendar.getInstance();
            t1.setNow(rightNow);
            t2.setNow(rightNow);

            // ********************************
            // This is the pivotal point of the
            // code - everything that could
            // fail is before this point.
            // Anything that makes persistent
            // changes to the state of the
            // system is after this point.
            // ********************************

            // Commit the change to the system state.
            // Importantly it won't throw.
            // Move
            PartOne swap1 = t1;
            t1 = p1;
            p1 = swap1;
            PartTwo swap2 = t2;
            t2 = p2;
            p2 = swap2;

          } finally {
            // frees the temporary if we passed the
            // pivot uneventfully.
            if (t2 != null)
              t2.close();  // TODO: catch possible exceptions
          }
        }
      } finally {
        // frees the temporary if we passed the
        // pivot uneventfully.
        if (t1 != null)
          t1.close();
      }
    } // t1 not null
    return new Whole(this.p1, this.p2);
  } // copy constructor

  public static void main(String[] args) {
    try {
      Whole a = new Whole(Paths.get("p1"), Paths.get("p2"));
      System.out.println(a);
      Whole b = a.move(a);
      System.out.println(b);
    } 
    catch (IOException e) {
      e.printStackTrace();
    } 
    finally {
      String[] files = { "p1", "p2", "p1copy", "p2copy" };
      for (String file : files) {
        try {
          Files.delete(Paths.get(file));
        } catch (IOException e) {
          System.err.println("Couldn't delete file " + file);
        }
      } // end for
    } // end finally
  } // end main
} // end class Whole











































/*
// Move
PartOne swap1 = t1;
t1 = p1;
p1 = swap1;
PartTwo swap2 = t2;
t2 = p2;
p2 = swap2;
*/