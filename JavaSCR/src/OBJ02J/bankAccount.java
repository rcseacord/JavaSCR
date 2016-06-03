package OBJ02J;

public class bankAccount extends account {
	// Subclass handles authentication
	@Override
	boolean withdraw(double amount) throws IllegalAccessException {
		if (!securityCheck()) {
			throw new IllegalAccessException();
		}
		return super.withdraw(amount);
	}

	private final boolean securityCheck() {
		// Check that account management may proceed
		return true;
	}

	public static void main(String[] args) {
		account account = new bankAccount();
		// Enforce security manager check
		boolean result = false;
		try {
			result = account.withdraw(200.0);
		} catch (IllegalAccessException e) {
			System.err.println("Withdrawal failed.");
			e.printStackTrace();
		}
		System.out.println("Withdrawal successful? " + result);

		// Client uses new overdraft method.
		/*
		if (!result) {
			result = account.overdraft();
		}
		*/
		System.out.println("Withdrawal successful? " + result);
	}
}