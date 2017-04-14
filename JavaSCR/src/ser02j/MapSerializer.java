// The MIT License (MIT)
// 
// Copyright (c) 2017 Robert C. Seacord
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

package ser02j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

import org.bouncycastle.util.test.SimpleTestResult;

public class MapSerializer {
  public static SerializableMap<String, Integer> buildMap() {
    SerializableMap<String, Integer> map = new SerializableMap<String, Integer>();
    map.setData("John Doe", new Integer(123456789));
    map.setData("Richard Roe", new Integer(246813579));
    return map;
  }

  public static void InspectMap(SerializableMap<String, Integer> map) {
    System.out.println("John Doe's number is " + map.getData("John Doe"));
    System.out.println("Richard Roe's number is " + map.getData("Richard Roe"));
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    // Build map
    SerializableMap<String, Integer> map = buildMap();

    try {
    // Generate signing public/private key pair & sign map
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
    KeyPair kp = kpg.generateKeyPair();
    Signature sig = Signature.getInstance("SHA1withDSA");
    SignedObject signedMap = new SignedObject(map, kp.getPrivate(), sig);

    // Generate sealing key & seal map
    KeyGenerator generator;
    generator = KeyGenerator.getInstance("AES");
    generator.init(new SecureRandom());
    Key key = generator.generateKey();
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    SealedObject sealedMap = new SealedObject(signedMap, cipher);

    // Serialize map
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data"));
    out.writeObject(sealedMap);
    out.close();

    // Deserialize map
    ObjectInputStream in = new ObjectInputStream(new FileInputStream("data"));
    sealedMap = (SealedObject) in.readObject();
    in.close();

    // Unseal map
    cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);
    signedMap = (SignedObject) sealedMap.getObject(cipher);

    // Verify signature and retrieve map
    if (!signedMap.verify(kp.getPublic(), sig)) {
      System.err.println("Map failed verification");
    }
    
    map = (SerializableMap<String, Integer>) signedMap.getObject();
    }
    catch (InvalidKeyException | NoSuchAlgorithmException | 
        ClassNotFoundException | IOException | BadPaddingException | 
        IllegalBlockSizeException | NoSuchPaddingException | SignatureException e) {
      e.printStackTrace();
    }

    // Inspect map
    InspectMap(map);
  }
}