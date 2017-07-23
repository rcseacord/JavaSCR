package acc04j;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.lang.Object;

import sun.misc.Unsafe;

public class NotSafe {

  public static Unsafe getUnsafeObject() throws ClassNotFoundException, NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {
    Class<?> u = Class.forName("sun.misc.Unsafe");
    Field f = u.getDeclaredField("theUnsafe");
    f.setAccessible(true);
    return (Unsafe) f.get(null);
  }

  public static Class<? extends Object> getUnsafeObjectTheHardWay()
      throws IllegalAccessException, SecurityException, NoSuchFieldException {
    ByteBuffer bb = ByteBuffer.allocateDirect(8192);
    bb.putInt(0x41414141);
    bb.putInt(0x42424242);

    Class<? extends ByteBuffer> c = bb.getClass();
    System.out.println(c);
    // class java.nio.DirectByteBuffer caches an unsafe-access object in
    // field:
    // protected static final Unsafe unsafe = Bits.unsafe();
    Field unsafe_field = bb.getClass().getDeclaredField("unsafe");
    System.out.println(unsafe_field);
    unsafe_field.setAccessible(true);
    // Reading the value of the unsafe field to get the unsafe object handle
    Object unsafe = unsafe_field.get(null);
    System.out.println(unsafe);
    Class<? extends Object> unsafeClass = unsafe.getClass();
    return unsafeClass;
  }

  public static void main(String[] argv) throws NoSuchFieldException, SecurityException, ClassNotFoundException,
      IllegalArgumentException, IllegalAccessException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      System.out.println("Security manager installed.");
    } else {
      System.out.println("No security manager.");
    }

    // get the offset of the "standard" error output stream.
    Unsafe unsafe = getUnsafeObject();
    Field f = System.class.getDeclaredField("err");
    System.out.println(unsafe.staticFieldOffset(f));
  } // end main
} // end NotSafe class
