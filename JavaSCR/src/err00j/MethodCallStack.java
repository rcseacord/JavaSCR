package err00j;

import java.lang.UnsupportedOperationException;

class MethodCallStack {

	public static void main(String[] args) {
		System.out.println("Enter main()");
		try {
		  methodA();
		}
		catch (UnsupportedOperationException uoe) {
			uoe.printStackTrace();
		}
		System.out.println("Exit main()");
	}

	private static void methodA() {
		System.out.println("Enter methodA()");
		try {
			  methodB();
			}
			finally {
				System.out.println("methodA() finally block");
			}
		System.out.println("Exit methodA()");
	}

	private static void methodB() {
		System.out.println("Enter methodB()");
		try {
			  methodC();
			}
			finally {
				System.out.println("methodB() finally block");
			}
		System.out.println("Exit methodB()");
	}

	private static void methodC() {
		System.out.println("Enter methodC()");
		try {
		  methodD();
		}
		finally {
			System.out.println("methodC() finally block");
		}
		System.out.println("Exit methodC()");
	}
	
	private static void methodD() {
		System.out.println("Enter methodD()");
		throw new UnsupportedOperationException();
		// unreachable
	}
}
