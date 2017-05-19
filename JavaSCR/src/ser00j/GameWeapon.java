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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class GameWeapon implements Serializable {
  private static final long serialVersionUID = -2219161247533868418L;
  private WeaponStore ws = new WeaponStore();
  private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("ws", WeaponStore.class) }; //$NON-NLS-1$

  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ObjectInputStream.GetField gf = ois.readFields();
    this.ws = (WeaponStore) gf.get("ws", this.ws); //$NON-NLS-1$
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    ObjectOutputStream.PutField pf = oos.putFields();
    pf.put("ws", this.ws); //$NON-NLS-1$
    oos.writeFields();
  }

  @Override
  public String toString() {
    return String.valueOf(this.ws);
  }

  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
    GameWeapon gw = new GameWeapon();

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempdata.ser"));) { //$NON-NLS-1$
      oos.writeObject(gw);
    }
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempdata.ser")); //$NON-NLS-1$
    ) {
      gw = (GameWeapon) ois.readObject();
      System.out.println("No. of Weapons = " + gw.ws.numOfWeapons); //$NON-NLS-1$
    }

    // Clean up the file
    new File("tempdata.ser").delete(); //$NON-NLS-1$
  } // end main

}