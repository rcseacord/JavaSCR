package IDS08J;

class ReDoS {
	public static void main(String[] args) {
		final String a002 = "aa"; // two 'a' //$NON-NLS-1$
		final String a003 = "aaa";  // three 'a' //$NON-NLS-1$
		// 102 'a'
		final String a102 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //$NON-NLS-1$
		// 103 'a'
		final String a103 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //$NON-NLS-1$

		final String ax4 = "aaaaX";	 //$NON-NLS-1$
		final String ax16 = "aaaaaaaaaaaaaaaaX"; //$NON-NLS-1$
		final String ax32 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX"; //$NON-NLS-1$
		
		System.out.println(ax4.matches("^(a+)+$")); //$NON-NLS-1$
		System.out.println(ax16.matches("^(a+)+$")); //$NON-NLS-1$
		System.out.println(ax32.matches("^(a+)+$")); //$NON-NLS-1$
		
		System.out.println(a002.matches("(aa|aab?)*")); //$NON-NLS-1$
		System.out.println(a003.matches("(aa|aab?)*")); //$NON-NLS-1$
		System.out.println(a102.matches("(aa|aab?)*")); //$NON-NLS-1$
		System.out.println("++++++++++++++++++++++"); //$NON-NLS-1$
		System.out.println(a103.matches("(aa|aab?)*")); //$NON-NLS-1$
		System.out.println("++++++++++++++++++++++"); //$NON-NLS-1$
	}
}
