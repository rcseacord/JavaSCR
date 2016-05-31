package MET12J;

import java.util.Date;

class subClass extends baseClass {
	  private Date d; // Mutable instance field
	 
	  protected subClass() {
	    d = new Date();
	  }
	 
	// The class SubClass overrides the protected finalize() method
	// and performs cleanup activities.
	protected void finalize() throws Throwable {
		System.out.println("Subclass finalize!");
		try {
			// Cleanup resources
			d = null;
		} finally {
			// Subsequently, it calls super.finalize() to make sure its
			// superclass is also finalized. 
			super.finalize(); // Call BaseClass's finalizer
		}
	}
	 
	  public void doLogic() throws Throwable {
	    // Any resource allocations made here will persist
	 
	    // Inconsistent object state
	    System.out.println(
	        "This is sub-class! The date object is: " + d);
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