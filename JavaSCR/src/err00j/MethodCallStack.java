package err00j;

import java.lang.UnsupportedOperationException;

class MethodCallStack {

	public static void main(String[] args) {
		System.out.println("Enter main()"); //$NON-NLS-1$
		try {
		  methodA();
		}
		catch (UnsupportedOperationException uoe) {
			uoe.printStackTrace();
		}
		System.out.println("Exit main()"); //$NON-NLS-1$
	}

	private static void methodA() {
		System.out.println("Enter methodA()"); //$NON-NLS-1$
		try {
			  methodB();
			}
			finally {
				System.out.println("methodA() finally block"); //$NON-NLS-1$
			}
		System.out.println("Exit methodA()"); //$NON-NLS-1$
	}

	private static void methodB() {
		System.out.println("Enter methodB()"); //$NON-NLS-1$
		try {
			  methodC();
			}
			finally {
				System.out.println("methodB() finally block"); //$NON-NLS-1$
			}
		System.out.println("Exit methodB()"); //$NON-NLS-1$
	}

	private static void methodC() {
		System.out.println("Enter methodC()"); //$NON-NLS-1$
		try {
		  methodD();
		}
		finally {
			System.out.println("methodC() finally block"); //$NON-NLS-1$
		}
		System.out.println("Exit methodC()"); //$NON-NLS-1$
	}
	
	private static void methodD() {
		System.out.println("Enter methodD()"); //$NON-NLS-1$
		throw new UnsupportedOperationException();
		// unreachable
	}
}
