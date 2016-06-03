package OBJ11J;

public class storage {
	private static bankOperations bop;

	public static void store(bankOperations bo) {
		// Store only if it is initialized
		if (bop == null) {
			if (bo == null) {
				System.out.println("Invalid object!");
				System.exit(1);
			}
			bop = bo;
		}
	}
}