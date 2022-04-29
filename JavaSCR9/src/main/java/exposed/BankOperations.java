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

package exposed;

class BankOperations {
  final private String user;
	// Constructor throws a SecurityException when SSN verification fails.
	public BankOperations(String user) {
	  this.user = user;
		// Perform social security number (SSN) verification  
		if (!performSSNVerification()) {
			throw new SecurityException("Access Denied!"); //$NON-NLS-1$
			// The garbage collector waits to grab the object
			// reference. However, the object cannot be garbage-collected
			// until after the finalizer completes its execution.
		}
	}

	// Returns true if data entered is valid, else false
	// Assume that the attacker always enters an invalid SSN
	private static boolean performSSNVerification() {
		return false;
	}

	public void greet() {
		System.out.println("Welcome " + this.user); //$NON-NLS-1$
	}
}