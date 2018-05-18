// The MIT License (MIT)
// 
// Copyright (c) 2016 Robert C. Seacord
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

package MET08J;

import com.google.common.testing.EqualsTester;

import java.util.Objects;

public class Card {
	private final int number;

	public Card(int number) {
		this.number = number;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Card)) return false;
    Card card = (Card) o;
    return number == card.number;
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  public static void main(String[] args) {
    Card p1 = new Card(1);
    Card p2 = new Card(1);
    Card p3 = new Card(1);
    Card p4 = new Card(2);
    Card p5 = new Card(2);

    System.out.println(p1.equals(p2)); // Returns true
    System.out.println(p2.equals(p3)); // Returns true
    System.out.println(p1.equals(p3)); // Returns true

    new EqualsTester()
        .addEqualityGroup(p1, p2, p3)
        .addEqualityGroup(p4, p5)
        .testEquals();

  } // end main
} // end class