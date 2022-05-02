// The MIT License (MIT)
// 
// Copyright (c) 2022 Robert C. Seacord
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

import serial.Serial;

import java.io.*;

public class GameWeapon implements Serializable {
  @java.io.Serial
  private static final long serialVersionUID = -2219161247533868418L;

  private final String Weapon = "BFG";
  @java.io.Serial
  private static final ObjectStreamField[] serialPersistentFields = {
      new ObjectStreamField("ws", WeaponStore.class)
  };

  // What happens if transient and serialPersistentFields conflict?
  /* transient */ private final WeaponStore ws;

  public GameWeapon() {
    ws = new WeaponStore();
  }

  @Override
  public String toString() {
    return String.valueOf(this.ws);
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    GameWeapon gw = new GameWeapon();
    gw = (GameWeapon) Serial.deserialize(Serial.serialize(gw));
    System.out.println("No. of Weapons = " + gw.ws.numOfWeapons);
    System.out.println("Weapon = " + gw.Weapon);
  }
} // end main
