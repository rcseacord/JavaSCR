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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionReporterExample {

	private static FileInputStream fis;
	
	private static final Logger log = Logger.getLogger(ExceptionReporterExample.class.getName());
	static ExceptionReporter er = new ExceptionReporter();

	// Exception reporter that logs the exception
	private static final Reporter logException = new Reporter() {
		public void report(Throwable t) {
			log.log(Level.SEVERE, t.toString(), t);
			System.exit(-1);
		}
	};
	
	static Reporter old_reporter = er.setExceptionReporter(logException);
	
	public static void main(String[] args) throws FileNotFoundException {
		try {
			setFis(new FileInputStream(System.getenv("APPDATA") + "\\" + args[0]));
		} catch (IOException ioex) {		
			er.reportException(ioex);
			// Recover from the exception...
		}

	}

	public static FileInputStream getFis() {
		return fis;
	}

	public static void setFis(FileInputStream fis) {
		ExceptionExample.fis = fis;
	}

}
