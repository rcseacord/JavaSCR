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

package equality;

import com.google.common.testing.EqualsTester;

import java.util.Objects;

class xCard extends Card {
  final private String suit;

  private xCard(int number, String type) {
    super(number);
    this.suit = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Card)) return false;

    // Normal Card, do not compare type
    if (!(o instanceof xCard)) return o.equals(this);

    // It is an xCard, compare type as well
    xCard xc = (xCard) o;
    return super.equals(o) && Objects.equals(suit, xc.suit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), suit);
  }

  public static void main(String[] args) {
    xCard p1 = new xCard(1, "heart");
    Card p2 = new Card(1);
    xCard p3 = new xCard(1, "diamond");

    // p1 and p2 compare equal and p2 and p3 compare equal,
    // but p1 and p3 compare unequal, violating the transitivity requirement.

    // The Card class has no knowledge of the XCard class
    // and consequently cannot determine that p2 and p3 have different values
    // for the field type.
    System.out.println(p1.equals(p2)); // Returns true
    System.out.println(p2.equals(p3)); // Returns true
    System.out.println(p1.equals(p3)); // Returns false, violating transitivity

    new EqualsTester()
        .addEqualityGroup(p1, p2)
        .addEqualityGroup(p3)
        .testEquals();
  } // end main
} // end class xCard