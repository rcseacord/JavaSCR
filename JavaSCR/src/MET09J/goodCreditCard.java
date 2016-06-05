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

package MET09J;

import java.util.HashMap;
import java.util.Map;

public final class goodCreditCard {
	  private final int number;
	   
	  public goodCreditCard(int number) {
	    this.number = number;
	  }
	 
	  public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (!(o instanceof goodCreditCard)) {
	      return false;
	    }
	    goodCreditCard cc = (goodCreditCard)o;
	    return cc.number == number;
	  }
	 
	  // override the hashCode() method so that it generates the same 
	  // value for any two instances that are considered to be equal
	  // by the equals() method. 
	  public int hashCode() {
	    int result = 17;
	    result = 31 * result + number;
	    return result;
	  }
	 
	  public static void main(String[] args) {
	    Map<goodCreditCard, String> m = new HashMap<goodCreditCard, String>();
	    m.put(new goodCreditCard(100), "4111111111111111");
	    System.out.println(m.get(new goodCreditCard(100)));
	  }
	}