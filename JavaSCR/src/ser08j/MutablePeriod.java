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

package ser08j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MutablePeriod {
  // A period instance
  public Period period = null;
  // period's start field, to which we shouldn't have access
  public Date start = null;
  // period's end field, to which we shouldn't have access
  public Date end = null;

  public MutablePeriod() {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);) {
      // Serialize a valid Period instance
      oos.writeObject(new Period(new Date(), new Date()));

      // Append rogue previous object references for internal Date fields in Period.
      byte[] ref = { 0x71,  0x00, 0x7e, 0x00, 0x05 }; // Reference #5
      baos.write(ref); // The start field
      ref[4] = 0x04; // Reference # 4
      baos.write(ref); // The end field

      try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
        // Deserialize Period and "stolen" Date references
        this.period = (Period) ois.readObject();
        this.start = (Date) ois.readObject();
        this.end = (Date) ois.readObject();
      } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
      } 
    } // end try-with-resources
    catch (IOException e1) {
      e1.printStackTrace();
    }
  } // end MutablePeriod constructor

  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    System.out.println("MutablePeriod");
    MutablePeriod mp = new MutablePeriod();
    Period p = mp.period;
    Date pEnd = mp.end;
    // Let's turn back the clock
    pEnd.setYear(78);
    System.out.println(p);
    // Bring back the 60s!
    pEnd.setYear(69);
    System.out.println(p);
  }
}