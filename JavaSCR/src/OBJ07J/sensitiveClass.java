package OBJ07J;

class sensitiveClass {
	private char[] filename;
	private Boolean shared = false;

	sensitiveClass(String filename) {
		this.filename = filename.toCharArray();
	}

	// When a client requests a String instance by invoking the get() method,
	// the shared flag is set. To maintain the array's consistency with the
	// returned String object, operations that can modify the array are
	// subsequently prohibited.
	final String get() {
		if (!shared) {
			shared = true;
			return String.valueOf(filename);
		} else {
			throw new IllegalStateException("Failed to get instance");
		}
	}

	// The replace() method will not replace all elements of the array with
	// an x when the shared flag is set.
	final void replace() {
		if (!shared) {
			for (int i = 0; i < filename.length; i++) {
				filename[i] = 'x';
			}
		}
	}
	
	final void printFilename() {
		System.out.println(String.valueOf(filename));
	}
	
	// Prevent subclasses from being made cloneable by defining a final clone()
	// method that always fails.
	/*
	public final sensitiveClass clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	*/

	public static void main(String[] args) {
		sensitiveClass ms1 = new sensitiveClass("password.txt");
		String s = ms1.get(); // Returns filename
		System.out.println(s); // Filename is "file.txt"
		ms1.replace(); // Attempts to replaces all characters with 'x'
		ms1.printFilename(); // Filename unchanged
	}
}