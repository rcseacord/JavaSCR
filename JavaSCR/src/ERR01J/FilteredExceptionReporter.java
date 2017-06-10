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

import java.util.logging.Logger;

class FilteredExceptionReporter extends ExceptionReporter {
	private static final Logger logger = Logger.getLogger("com.organization.Log"); //$NON-NLS-1$

	/*
	public static void report(Throwable t) {
		try {
			final Throwable filteredException = (t instanceof SecureExceptions) ? t : filter(t);
		} finally {
			// Do any necessary user reporting
			// (show dialog box or send to console)
			if (filteredException instanceof NonSensitiveCommonException) {
				logger.log(Level.FINEST, "Loggable exception occurred", t);
			}
		}
	}

	public static Exception filter(Throwable t) {
		if (t instanceof SensitiveForLoggingException_1) {
			// Do not log sensitive information (whitelist)
			return SensitiveCommonException();
		}
		// ...
		// Return for reporting to the user
		return new NonSensitiveCommonException();
	}
	*/
}