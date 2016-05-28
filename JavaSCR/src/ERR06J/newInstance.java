package ERR06J;

public class newInstance {
	  private static Throwable throwable;
	 
	  private newInstance() throws Throwable {
	    throw throwable;
	  }
	 
	  public static synchronized void undeclaredThrow(Throwable throwable) {
	    // These exceptions should not be passed
	    if (throwable instanceof IllegalAccessException ||
	        throwable instanceof InstantiationException) {
	      // Unchecked, no declaration required
	      throw new IllegalArgumentException();
	    }
	 
	    newInstance.throwable = throwable;
	    try {
	      // Next line throws the Throwable argument passed in above,
	      // even though the throws clause of class.newInstance fails
	      // to declare that this may happen
	      newInstance.class.newInstance();
	    } catch (InstantiationException e) { /* Unreachable */
	    } catch (IllegalAccessException e) { /* Unreachable */
	    } finally { // Avoid memory leak
	      newInstance.throwable = null;
	    }
	  }
	 
	  public static void main(String[] args) {  
	    // No declared checked exceptions
	    newInstance.undeclaredThrow(
	        new Exception("Any checked exception"));
	  }
	}
