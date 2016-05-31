package MET12J;

class baseClass {
	protected void finalize() throws Throwable {
		System.out.println("Superclass finalize!");
		// Calls the doLogic() method, which happens to be overridden in the
		// SubClass. This resurrects a reference to SubClass that not only
		// prevents it from being garbage-collected but also prevents it
		// from calling its finalizer to close new resources that may have
		// been allocated by the called method.
		doLogic();
	}

	public void doLogic() throws Throwable {
		System.out.println("This is super-class!");
	}
}