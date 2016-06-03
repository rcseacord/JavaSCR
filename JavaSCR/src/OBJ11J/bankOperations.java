package OBJ11J;

public class bankOperations {
	// Constructor throws a SecurityException when SSN verification fails.
	public bankOperations() {
		if (!performSSNVerification()) {
			throw new SecurityException("Access Denied!");
			// The garbage collector waits to grab the object
			// reference. However, the object cannot be garbage-collected
			// until after the finalizer completes its execution.
		}
	}

	// Returns true if data entered is valid, else false
	// Assume that the attacker always enters an invalid SSN
	private boolean performSSNVerification() {
		return false;
	}

	public void greet() {
		System.out.println("Welcome user! You may now use all the features.");
	}
}