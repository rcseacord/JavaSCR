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

package ERR08J;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ERR01J.ExceptionReporter;

class divideException {
  private static int division(int totalSum, int totalNumber) throws IOException {
    int average  = totalSum / totalNumber;
    // Additional operations that may throw IOException...
    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div.result"))) //$NON-NLS-1$
    ) {
      out.write(average);
    }
    return average;
  }
  
  public static void main(String[] args) {
    try {
    	System.out.println(division(200, 5));
    	System.out.println(division(200, 0)); // Divide by zero
    } catch (Exception e) {
		ExceptionReporter er = new ExceptionReporter();
		er.reportException(e);
    }
  }
}