package obj14j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class Leak {
  private static final int Bx41 = 0x41;
  private static final int Bx42 = 0x42;
  private static final int Bx43 = 0x43;
  private static final int Bx44 = 0x44;
  @SuppressWarnings("unused")
  private static byte[] keyBytes = new byte[] {Bx41, Bx42, Bx43, Bx44};
  @SuppressWarnings("unused")
  private static SecretKeySpec key;
  @SuppressWarnings("unused")
  private static Cipher cipher;
  
} // end class Leak
