package OBJ07J;

class malSubclass extends sensitiveClass implements Cloneable {
	protected malSubclass(String filename) {
		super(filename);
	}

	@Override
	public malSubclass clone() { // Well-behaved clone() method
		malSubclass s = null;
		try {
			s = (malSubclass) super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("not cloneable");
		}
		return s;
	}

	public static void main(String[] args) {
		// Java's cloning feature provides a way to circumvent the sharing
		// constraint even though SensitiveClass does not implement the
		// Cloneable interface.
// malSubclass ms1 = new malSubclass("file.txt");
		malSubclass ms1 = new malSubclass("file.txt");
		malSubclass ms2 = ms1.clone(); // Creates a copy by cloning ms1
		String s = ms1.get(); // Returns filename
		System.out.println(s); // Filename is "file.txt"
		// Because the second instance ms2 does not have its shared flag set to
		// true, it is possible to alter the first instance ms1 using the
		// replace() method.
		ms2.replace(); // Replaces all characters with 'x'
		// ms1.get() and ms2.get() will subsequently return filename =
		// 'xxxxxxxx'
		ms1.printFilename(); // Filename becomes 'xxxxxxxx'
		ms2.printFilename(); // Filename becomes 'xxxxxxxx'
	}
}