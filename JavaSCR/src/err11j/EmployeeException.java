package err11j;

public class EmployeeException extends Exception {
	private static final long serialVersionUID = 1426450337125104125L;

	public EmployeeException(String message) {
        super(message);
    }
 
    public EmployeeException(String message, Throwable cause) {
        super(message, cause);
    }
}