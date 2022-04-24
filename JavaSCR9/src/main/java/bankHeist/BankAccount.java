// The MIT License (MIT)
// 
// Copyright (c) 2022 Robert C. Seacord
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

package bankHeist;

class BankAccount extends Account {
	// Subclass handles authentication
	@Override
	void withdraw(double amount) throws SecurityException {
		if (!securityCheck()) {
			throw new SecurityException();
		}
		super.withdraw(amount);
	}

	private static boolean securityCheck() {
		// Check that account management may proceed
		return false;
	}

	public static void main(String[] args) {
		Account account = new BankAccount();
		// Enforce security manager check
		try {
			account.withdraw(200.0);
		  System.out.println("Withdrawal successful.");
		} catch (SecurityException e) {
			System.err.println("Withdrawal failed.");
			e.printStackTrace();
		}

		// Client uses new overdraft method.
		// System.out.println("Withdrawal successful? " + account.overdraft());
	}
}

































//  boolean overdraft() {
//    if (!securityCheck()) {
//      throw new SecurityException();
//    }
//    return super.overdraft();
//  }