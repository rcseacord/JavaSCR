package ERR01J;

import java.security.BasicPermission;

final class ExceptionReporterPermission extends BasicPermission {
	public ExceptionReporterPermission(String permName) {
		super(permName); 
	} 
	// Even though the actions parameter is ignored, 
	// this constructor has to be defined 
	public ExceptionReporterPermission(String permName, String actions) { 
		super(permName, actions); 
	} 
}