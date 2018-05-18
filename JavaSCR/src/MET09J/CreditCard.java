// The MIT License (MIT)
// 
// Copyright (c) 2018 Robert C. Seacord
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

package MET09J;

import com.google.common.testing.EqualsTester;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class CreditCard {
  private final int number;

  private CreditCard(int number) {
    this.number = number;
  }

//	  @Override
//    public boolean equals(Object o) {
//	    if (o == this) {
//	      return true;
//	    }
//	    if (!(o instanceof badCreditCard)) {
//	      return false;
//	    }
//	    badCreditCard cc = (badCreditCard)o;
//	    return cc.number == number;
//	  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CreditCard)) return false;
    CreditCard that = (CreditCard) o;
    return number == that.number;
  }

  public static void main(String[] args) {
    CreditCard cc_a = new CreditCard(100);
    CreditCard cc_b = new CreditCard(100);
    CreditCard cc_c = new CreditCard(200);
    Map<CreditCard, String> m = new HashMap<>();
    m.put(cc_a, "4111111111111111");
    // The expected retrieved value is 4111111111111111;
    // the actual retrieved value is null.
    System.out.println(m.get(new CreditCard(100)));

    new EqualsTester()
        .addEqualityGroup(cc_a, cc_b)
        .addEqualityGroup(cc_c)
        .testEquals();
  }
}














































//  @Override
//  public int hashCode() {
//    return Objects.hash(number);
//  }