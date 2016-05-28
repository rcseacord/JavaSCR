package ERR01J;

import java.util.logging.Logger;

public class FilteredExceptionReporter extends ExceptionReporter {
	private static final Logger logger = Logger.getLogger("com.organization.Log");

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