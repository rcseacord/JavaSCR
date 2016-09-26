package OBJ03J;

public class UnsafeVarargs {
	static <T> T[] asArray(T... args) {
		return args;
	}

	static <T> T[] arrayOfTwo(T a, T b) {
		return asArray(a, b);
	}

	public static void main(String[] args) {
		String[] bar = arrayOfTwo("hi", "mom");
	}
}
