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