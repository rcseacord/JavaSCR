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


class xCardGood {
  final private String type;
  
  // Composition: private card field added
  final private card card;
 
  private xCardGood(int number, String type) {
    card = new card(number);
    this.type = type;
  }
 
  // new viewCard() method added
  public card viewCard() {
    return card;
  }
 
  public boolean equals(Object o) {
    if (!(o instanceof xCardGood)) {
      return false;
    }
 
    xCardGood cp = (xCardGood)o;
    return cp.card.equals(card) && cp.type.equals(type);
  }
 
	// Comply with MET09-J
	public int hashCode() {
		/* ... */
		return 0;
	}
 
  public static void main(String[] args) {
    xCardGood p1 = new xCardGood(1, "type1");
    card p2 = new card(1);
    xCardGood p3 = new xCardGood(1, "type2");
    xCardGood p4 = new xCardGood(1, "type1");
    System.out.println(p1.equals(p2)); // Prints false
    System.out.println(p2.equals(p3)); // Prints false
    System.out.println(p1.equals(p3)); // Prints false
    System.out.println(p1.equals(p4)); // Prints true
  }
}