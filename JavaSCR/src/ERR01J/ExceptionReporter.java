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

package ERR01J;

public class ExceptionReporter {

	// Exception reporter that prints the exception
	// to the console (used as default)
	private static final Reporter PrintException = t -> System.err.println(t.toString());

	// Stores the default reporter
	// The current reporter can be changed by the user
	private Reporter current = PrintException;
	
	public void reportException(Throwable t) {
		current.report(t);
	}

	// May throw an unchecked SecurityException
	public Reporter setExceptionReporter(Reporter reporter) {
			ExceptionReporterPermission perm = new ExceptionReporterPermission("exc.reporter");
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(perm);
		}
		Reporter old = current;
		current = reporter;
		return old;
	}
}
