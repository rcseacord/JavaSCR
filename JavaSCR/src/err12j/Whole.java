package err12j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

public class Whole {

  private PartOne p1;
  private PartTwo p2;

  public Whole(Path path1, Path path2) throws IOException {
    p1 = new PartOne(path1);
    p2 = new PartTwo(path2);
  }

  public Whole(PartOne pone, PartTwo ptwo) {
    p1 = pone;
    p2 = ptwo;
  }

  public PartOne getp1() {
    return p1;
  }

  public PartTwo getp2() {
    return p2;
  }

  public Whole copy(Whole Source) throws IOException {
    PartOne t1 = new PartOne(Source.getp1());
    if (t1 != null) { // not necessary for copy constructor
      try {
        PartTwo t2 = new PartTwo(Source.getp2());
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
            // Nothing that makes persistent
            // changes to the state of the
            // system is before this point.
            // ********************************

            // Commit the change to the system state.
            // Importantly it won't throw.
            p1 = t1;
            t1 = null;
            p2 = t2;
            t2 = null;

          } finally {
            // frees the temporary if we passed the
            // pivot uneventfully.
            if (t2 != null)
              t2.close();
          }
        }
      } finally {
        // frees the temporary if we passed the
        // pivot uneventfully.
        if (t1 != null)
          t1.close();
      }
    } // t1 not null
    return new Whole(p1, p2);
  } // clone method

  public static void main(String[] args) {
    try {
      Whole a = new Whole(Paths.get("p1"), Paths.get("p2"));
      System.out.println(a.toString());
      Whole b = a.copy(a);
      System.out.println(b.toString());
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