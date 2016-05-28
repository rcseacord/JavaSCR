package ERR03J;

import ERR01J.ExceptionReporter;

class Dimensions {
	private int l, w, h;
	static public final int PAD = 2;
	static public final int MAX_DIM = 12;

	public Dimensions(int l, int w, int h) {
		this.l = l;
		this.w = w;
		this.h = h;
		/*
		if (l <= PAD || w <= PAD || h <= PAD || l > MAX_DIM + PAD ||
			w > MAX_DIM + PAD || h > MAX_DIM + PAD) {
			throw new IllegalArgumentException();
		} 
		*/
	}

	protected int getVolumePackage(int weight) {
		l += PAD; w += PAD; h += PAD;
		try {
			if (weight <= 0 || weight > 20) 
				throw new IllegalArgumentException();
			int volume = l * w * h; // 12 * 12 * 12 = 1728
			l -= PAD;
			w -= PAD;
			h -= PAD; // Revert
			return volume;
		} catch (Throwable t) {
			ExceptionReporter er = new ExceptionReporter();
			er.reportException(t);  // Report
			return -1; // Non-positive error code
		}
	}
	
	protected  int getVolumePackageGood(int weight) {
			try {
			if (weight <= 0 || weight > 20) 
				throw new IllegalArgumentException();
			return (l + PAD) * (w + PAD) * (h + PAD); 
		} catch (Throwable t) {
			ExceptionReporter er = new ExceptionReporter();
			er.reportException(t);  // Report
			return -1; // Non-positive error code
		}
	}

	public static void main(String[] args) {
		Dimensions d = new Dimensions(10, 10, 10);
		System.out.println(d.getVolumePackageGood(21)); // Prints -1 (error)
		System.out.println(d.getVolumePackageGood(19)); // Prints 2744 instead of 1728
	}
}