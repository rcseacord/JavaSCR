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

package MET06J;

import java.net.HttpCookie;

class BadClone implements Cloneable {
	private HttpCookie[] cookies;

	BadClone(HttpCookie[] c) {
			cookies = c;
	}

	public Object clone() throws CloneNotSupportedException {
		final BadClone clone = (BadClone) super.clone();
		// Invokes overridable method!!!
		clone.doSomething();
		// deepCopy() is called after doSomething()
		clone.cookies = clone.deepCopy();
		return clone;
	}

	// This overridden method sets the value of the cookies
	void doSomething() { // Overridable
		// Initializes hc to correct values
		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setValue("" + i * 2);
		}
	}

	void printValues() { // Overridable
		for (HttpCookie cookie : cookies) {
			System.out.println("value: " + cookie.getValue() + ", domain:" + cookie.getDomain());
		}
	}

	private HttpCookie[] deepCopy() {
		// Deep copy
		HttpCookie[] cookiesCopy = new HttpCookie[cookies.length];

		for (int i = 0; i < cookies.length; i++) {
			// Manually create a copy of each element in array
			cookiesCopy[i] = (HttpCookie) cookies[i].clone();
		}
		return cookiesCopy;
	}
}