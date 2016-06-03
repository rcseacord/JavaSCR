package OBJ02J;

class account {
	// Maintains all banking-related data such as account balance
	private double balance = 100;

	boolean withdraw(double amount) throws IllegalAccessException {
		if ((balance - amount) >= 0) {
			balance -= amount;
			System.out.println("Withdrawal successful. The balance is : " + balance);
			return true;
		}
		return false;
	}

	// added in later release
	/*
	boolean overdraft() {
		balance += 300; // Add 300 in case there is an overdraft
		System.out.println("Added back-up amount. The balance is :" + balance);
		return true;
	}
	*/

}