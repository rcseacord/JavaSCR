package obj14j;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

class Spy {
  public static void main(String[] args) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      System.out.println("Security manager installed."); //$NON-NLS-1$
    } else {
      System.out.println("No security manager."); //$NON-NLS-1$
    }

    // Returns an array of Field objects reflecting
    // all the fields declared by the class (including private)
    final Field fields[] = Leak.class.getDeclaredFields();

    // Enumerate fields
    for (Field field : fields) {
      System.out.println("Field: " + field); //$NON-NLS-1$
      if (field.getType().isArray()) {
        try {
          Object array = field.get("keyBytes".getClass()); //$NON-NLS-1$
          int length = Array.getLength(array);
          for (int j = 0; j < length; j++) {
            System.out.println(Array.get(array, j));
          }
        } catch (IllegalArgumentException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  } // end main
}
