package err12j;

import java.util.Date;

public class Whole {

  private PartOne p1;
  private PartTwo p2;

  public Whole(Date date1, Date date2) {
    p1 = new PartOne(date1);
    p2 = new PartTwo(date2);
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

  public Whole clone(Whole Source) {
    PartOne t1 = Source.getp1().clone();
    if (t1 != null) {  // not necessary for clone 
      try {
        PartTwo t2 = Source.getp2().clone();
        if (t2 != null) {  // not necessary for clone
          try {
            // methods that might throw
            t1.setParent(this);
            t2.setParent(this);

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
            PartOne swap1 = t1;
            t1 = p1;
            p1 = swap1;
            PartTwo swap2 = t2;
            t2 = p2;
            p2 = swap2;
          } finally {
            // either frees the original
            // resources or of the
            // temporary - depending
            // whether we passed the
            // pivot uneventfully.
            t2.dispose();
          }
        }
      } finally {
        // either frees the original
        // resources or of the temporary
        // - depending whether we
        // passed the pivot uneventfully.
        t1.dispose();
      }
    } // t1 not null
    return new Whole(p1, p2);
  } // clone method

  public static void main(String[] args) {
    Whole a = new Whole(new Date(), new Date());
    System.out.println(a.toString());
    Whole b = a.clone(a);
    System.out.println(b.toString());
  }
} // end class Whole