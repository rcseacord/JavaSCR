// The MIT License (MIT)
//
// Copyright (c) 2019 Robert C. Seacord
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

import java.io.Serializable;
import java.util.Date;

public final class Period implements Serializable {
  private static final long serialVersionUID = 4933289900620142386L;
  private Date start;
  private Date end;

  /**
   * @param start the beginning of the period
   * @param end   the end of the period; must not precede start
   * @throws IllegalArgumentException if start is after end
   * @throws NullPointerException     if start or end is null
   */
  public Period(Date start, Date end) {
    // Make defensive copies of each mutable constructor parameter and 
    // use the copies in place of the originals
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());
    // Throw exception if arguments violate object invariant
    if (this.start.compareTo(this.end) > 0)
      throw new IllegalArgumentException(start + " after " + end);
  }

  public Date start() {
    // Do not return references to private mutable class members
    return new Date(this.start.getTime());
  }

  public Date end() {
    // Do not return references to private mutable class members
    return new Date(this.end.getTime());
  }

  @Override
  public String toString() {
    return this.start + " - " + this.end;
  }

  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    Date start = new Date();
    Date end = new Date();
    Period p = new Period(start, end);
    Date pEnd = p.end();
    // Let's turn back the clock
    pEnd.setYear(78);
    System.out.println(p);
  } // end main()

}  // end Serializable class Period









































// readObject method with defensive copying and validity checking
// This will defend against BogusPeriod and MutablePeriod attacks.
//private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
//  java.io.ObjectInputStream.GetField fields = ois.readFields();
//
//  // Defensively copy mutable fields
//  Date tempDate = ((Date) fields.get("start", new Date())); 
//  this.start = new Date(tempDate.getTime());
//  tempDate = ((Date) fields.get("end", new Date())); 
//  this.end = new Date(tempDate.getTime());
//
//  // Check that our invariants are satisfied
//  if (this.start.compareTo(this.end) > 0)
//    throw new InvalidObjectException(this.start + " after " + this.end);
//}