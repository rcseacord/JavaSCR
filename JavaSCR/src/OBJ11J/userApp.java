package OBJ11J;

public class userApp {
	public static void main(String[] args) {
		bankOperations bo;
		try {
			bo = new bankOperations();
		} catch (SecurityException ex) {
			bo = null;
		}

		storage.store(bo);
		System.out.println("Proceed with normal logic");
	}
}