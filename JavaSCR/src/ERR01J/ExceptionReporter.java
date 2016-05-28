package ERR01J;

public class ExceptionReporter {

	// Exception reporter that prints the exception
	// to the console (used as default)
	private static final Reporter PrintException = new Reporter() {
		public void report(Throwable t) {
			System.err.println(t.toString());
		}
	};

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
