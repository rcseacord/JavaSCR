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

package ser00j;

import java.io.*;

public class GameWeapon implements Serializable {
  private static final long serialVersionUID = -2219161247533868418L;
  private WeaponStore ws;
  private static final ObjectStreamField[] serialPersistentFields = {
      new ObjectStreamField("ws", WeaponStore.class)
  };

  public GameWeapon() {
    ws = new WeaponStore();
  }

  private static byte[] serialize(Object o) throws IOException {
    try (ByteArrayOutputStream ba = new ByteArrayOutputStream()) {
      try (ObjectOutputStream oos = new ObjectOutputStream(ba)) {
        oos.writeObject(o);
        return ba.toByteArray();
      }
    }
  }

  private static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
    Object obj;
    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
      obj = ois.readObject();
    }
    return obj;
  }

  @Override
  public String toString() {
    return String.valueOf(this.ws);
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    GameWeapon gw = new GameWeapon();
    gw = (GameWeapon) deserialize(serialize(gw));
    System.out.println("No. of Weapons = " + gw.ws.numOfWeapons);
  }
} // end main
