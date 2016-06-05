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

package MET06J;

import java.net.HttpCookie;

class subCloneExample extends badCloneExample {

	subCloneExample(HttpCookie[] c) {
		super(c);
		System.out.println("subCloneExample::ctor");
	}

	public Object clone() throws CloneNotSupportedException {
		System.out.println("subCloneExample::clone");
		final subCloneExample clone = (subCloneExample) super.clone();
		clone.doSomething();
		return clone;
	}

	void doSomething() { // Erroneously executed
		System.out.println("subCloneExample::doSomething");
		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setDomain(i + ".foo.com");
		}
	}

	void printValues() { // Overridable
		System.out.println("subCloneExample::printValues");
		for (int i = 0; i < cookies.length; i++) {
			System.out.println(cookies[i].getValue());
		}
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		HttpCookie[] hc = new HttpCookie[20];
		for (int i = 0; i < hc.length; i++) {
			hc[i] = new HttpCookie("cookie" + i, "0");
		}
		badCloneExample bc = new subCloneExample(hc);
		bc.clone();
		bc.printValues();
	}
}