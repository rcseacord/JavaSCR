package ser05j;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import java.io.ObjectInputFilter;

class BikeFilter implements ObjectInputFilter {

  // private final List<Function<Class<?>, Status>> filters;
  /**
   * Maximum allowed bytes in the stream.
   */
  private long maxStreamBytes;
  /**
   * Maximum depth of the graph allowed.
   */
  private long maxDepth;
  /**
   * Maximum number of references in a graph.
   */
  private long maxReferences;
  /**
   * Maximum length of any array.
   */
  private long maxArrayLength;

  @Override
  public Status checkInput(FilterInfo filterInfo) {
    System.out.println("filterInfo.references = " + filterInfo.references());
    System.out.println("filterInfo.depth = " + filterInfo.depth());
    System.out.println("filterInfo.streamBytes = " + filterInfo.streamBytes());
    System.out.println("filterInfo.references = " + filterInfo.references());
    System.out.println("filterInfo.depth = " + filterInfo.depth());
    System.out.println("filterInfo.streamBytes = " + filterInfo.streamBytes());
    System.out.println("filterInfo.serialClass = " + filterInfo.serialClass().getCanonicalName());

    if (filterInfo.references() < 0 || filterInfo.depth() < 0 || filterInfo.streamBytes() < 0
        || filterInfo.references() > maxReferences || filterInfo.depth() > maxDepth
        || filterInfo.streamBytes() > maxStreamBytes) {
      return Status.REJECTED;
    }
    if (ser05j.Bicycle.class == filterInfo.serialClass()) {
      return Status.ALLOWED;
     }
    else {
      return Status.REJECTED;
    }
      
    /*
    Class<?> clazz = filterInfo.serialClass();
    if (clazz != null) {
      if (clazz.isArray()) {
        if (filterInfo.arrayLength() >= 0 && filterInfo.arrayLength() > maxArrayLength) {
          // array length is too big
          return Status.REJECTED;
        }
        do {
          // Arrays are decided based on the component type
          clazz = clazz.getComponentType();
        } while (clazz.isArray());
      }

      if (clazz.isPrimitive()) {
        // Primitive types are allowed.  
        return Status.ALLOWED;
      } 
      else {
        // Find any filter that allowed or rejected the class
        final Class<?> cl = clazz;
        Optional<Status> status = filters.stream().map(f -> f.apply(cl)).filter(p -> p != Status.UNDECIDED).findFirst();
        return status.orElse(Status.UNDECIDED);
      }
    }
    return Status.UNDECIDED;
    */
  } // end checkInput
} // end class BikeFilter