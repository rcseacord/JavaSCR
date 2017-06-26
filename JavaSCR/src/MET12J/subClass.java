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

package MET12J;

import java.util.Date;

class subClass extends baseClass {
	  private Date d; // Mutable instance field
	 
	  protected subClass() {
	    this.d = new Date();
	  }
	 
	// The class SubClass overrides the protected finalize() method
	// and performs cleanup activities.
	@Override
  protected void finalize() throws Throwable {
		System.out.println("Subclass finalize!"); //$NON-NLS-1$
		try {
			// Cleanup resources
			this.d = null;
		} finally {
			// Subsequently, it calls super.finalize() to make sure its
			// superclass is also finalized. 
			super.finalize(); // Call BaseClass's finalizer
		}
	}
	 
	  @Override
    public void doLogic() throws Throwable {
	    // Any resource allocations made here will persist
	 
	    // Inconsistent object state
	    System.out.println(
	        "This is sub-class! The date object is: " + this.d); //$NON-NLS-1$
	    // 'd' is already null
	  }
	  
	  @SuppressWarnings("deprecation")
	public static void main(String[] args) {
		    try {
		      baseClass bc = new subClass();
		      // Artificially simulate finalization (do not do this)
		      System.runFinalizersOnExit(true);
		    } catch (Throwable t) {
		      // Handle error
		    }
		  }
	  
	}