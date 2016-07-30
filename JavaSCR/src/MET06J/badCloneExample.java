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

class badCloneExample implements Cloneable {
	HttpCookie[] cookies;

	badCloneExample(HttpCookie[] c) {
		System.out.println("badCloneExample::ctor");
		cookies = c;
	}

	public Object clone() throws CloneNotSupportedException {
		System.out.println("badCloneExample::clone");
		final badCloneExample clone = (badCloneExample) super.clone();
		clone.doSomething(); // Invokes overridable method
		clone.cookies = clone.deepCopy();
		return clone;
	}

	void doSomething() { // Overridable
		System.out.println("badCloneExample::doSomething");
		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setValue("" + i * 2);
		}
		return;
	}
	
	void printValues() { // Overridable
		System.out.println("badCloneExample::printValues");
		for (int i = 0; i < cookies.length; i++) {
			System.out.println(cookies[i].getValue());
		}
		return;
	}

	HttpCookie[] deepCopy() {
		System.out.println("badCloneExample::deepCopy");
		if (cookies == null) {
			throw new NullPointerException();
		}

		// Deep copy
		HttpCookie[] cookiesCopy = new HttpCookie[cookies.length];

		for (int i = 0; i < cookies.length; i++) {
			// Manually create a copy of each element in array
			cookiesCopy[i] = (HttpCookie) cookies[i].clone();
		}
		return cookiesCopy;

	}
}