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
	}
	
	void printValues() { // Overridable
		System.out.println("badCloneExample::printValues");
		for (int i = 0; i < cookies.length; i++) {
			System.out.println(cookies[i].getValue());
		}
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