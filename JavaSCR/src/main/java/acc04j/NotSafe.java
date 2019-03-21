// The MIT License (MIT)
//
// Copyright (c) 2019 Robert C. Seacord
// Cleaned up version of PoC by Jeff Dileo
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
    return unsafe.getClass();
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
