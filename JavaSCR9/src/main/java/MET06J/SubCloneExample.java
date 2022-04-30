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

package MET06J;

import java.net.HttpCookie;

class SubCloneExample extends BadClone {

	SubCloneExample(HttpCookie[] c) {
		super(c);
	}

	@Override
  public Object clone() throws CloneNotSupportedException {
		final SubCloneExample clone = (SubCloneExample) super.clone();
		clone.doSomething();
		return clone;
	}

	// Malicious override sets the values of the domain names.
	void doSomething() {
		// Erroneously called from badClone::clone()
		// Objects are modified before deep copy occurs
		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setDomain(i + ".foo.com");
		}
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		HttpCookie[] hc = new HttpCookie[5];
		// Cookie values initialized to "0"
		for (int i = 0; i < hc.length; i++) {
			hc[i] = new HttpCookie("cookie" + i, "0");
		}
		BadClone bc = new SubCloneExample(hc);
		bc.clone();
		bc.printValues();
	}
}