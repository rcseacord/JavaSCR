package ser09j;

import java.io.ObjectInputFilter;

class BikeFilter implements ObjectInputFilter {

  @Override
  public Status checkInput(FilterInfo filterInfo) {
    System.out.println("filterInfo.references = " + filterInfo.references()); 
    System.out.println("filterInfo.depth = " + filterInfo.depth()); 
    System.out.println("filterInfo.streamBytes = " + filterInfo.streamBytes());
    System.out.println("filterInfo.depth = " + filterInfo.depth()); 
    System.out.println("filterInfo.serialClass = " + filterInfo.serialClass().getCanonicalName());

    long maxStreamBytes = 78;   // Maximum allowed bytes in the stream.
    long maxDepth = 1;
    long maxReferences = 1;
    if (filterInfo.references() < 0 || filterInfo.depth() < 0 || filterInfo.streamBytes() < 0
        || filterInfo.references() > maxReferences || filterInfo.depth() > maxDepth
        || filterInfo.streamBytes() > maxStreamBytes) {
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