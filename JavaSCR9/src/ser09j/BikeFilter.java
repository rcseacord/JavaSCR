package ser09j;

import java.io.ObjectInputFilter;

class BikeFilter implements ObjectInputFilter {
  /**
   * Maximum allowed bytes in the stream.
   */
  private long maxStreamBytes = 78;
  /**
   * Maximum depth of the graph allowed.
   */
  private long maxDepth = 1;
  /**
   * Maximum number of references in a graph.
   */
  private long maxReferences = 1;

  @Override
  public Status checkInput(FilterInfo filterInfo) {
    System.out.println("filterInfo.references = " + filterInfo.references()); 
    System.out.println("filterInfo.depth = " + filterInfo.depth()); 
    System.out.println("filterInfo.streamBytes = " + filterInfo.streamBytes()); 
    System.out.println("filterInfo.references = " + filterInfo.references()); 
    System.out.println("filterInfo.depth = " + filterInfo.depth()); 
    System.out.println("filterInfo.serialClass = " + filterInfo.serialClass().getCanonicalName()); 

    if (filterInfo.references() < 0 || filterInfo.depth() < 0 || filterInfo.streamBytes() < 0
        || filterInfo.references() > this.maxReferences || filterInfo.depth() > this.maxDepth
        || filterInfo.streamBytes() > this.maxStreamBytes) {
      return Status.REJECTED;
    }
    Class<?> clazz = filterInfo.serialClass();
    if (clazz != null) {
      if (Bicycle.class == filterInfo.serialClass()) {
        return Status.ALLOWED;
      }
      else {
        return Status.REJECTED;
      }      
    }
    return Status.UNDECIDED;      
  } // end checkInput
} // end class BikeFilter