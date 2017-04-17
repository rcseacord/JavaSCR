package IDS08J;

class ReDoS {
	public static void main(String[] args) {
		final String a002 = "aa"; // two 'a'
		final String a003 = "aaa";  // three 'a'
		// 102 'a'
		final String a102 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		// 103 'a'
		final String a103 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

		final String ax4 = "aaaaX";	
		final String ax16 = "aaaaaaaaaaaaaaaaX";
		final String ax32 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX";
		
		System.out.println(ax4.matches("^(a+)+$"));
		System.out.println(ax16.matches("^(a+)+$"));
		System.out.println(ax32.matches("^(a+)+$"));
		
		System.out.println(a002.matches("(aa|aab?)*"));
		System.out.println(a003.matches("(aa|aab?)*"));
		System.out.println(a102.matches("(aa|aab?)*"));
		System.out.println("++++++++++++++++++++++");
		System.out.println(a103.matches("(aa|aab?)*"));
		System.out.println("++++++++++++++++++++++");
	}
}
