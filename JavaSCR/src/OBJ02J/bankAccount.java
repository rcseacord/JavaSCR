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