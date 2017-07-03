package OBJ03J;

class UnsafeVarargs {
	private static <T> T[] asArray(T... args) {
		return args;
	}

	private static <T> T[] arrayOfTwo(T a, T b) {
		return asArray(a, b);
	}

	public static void main(String[] args) {
		String[] bar = arrayOfTwo("hi", "mom"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
