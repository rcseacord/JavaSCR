//The MIT License (MIT)
//
//Copyright (c) 2020 Robert C. Seacord
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

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
