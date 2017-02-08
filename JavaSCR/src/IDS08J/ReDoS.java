package IDS08J;

public class ReDoS {
	public static void main(String[] args) {

		final String pattern = "(aa|aab?)*";

		// two 'a'
		final String a002 = "aa";
		// three 'a'
		final String a003 = "aaa";
		// 102 'a'
		final String a102 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		// 103 'a'
		final String a103 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

		System.out.println(a002.matches(pattern));
		System.out.println(a003.matches(pattern));
		System.out.println(a102.matches(pattern));
		System.out.println("++++++++++++++++++++++");
		System.out.println(a103.matches(pattern));
		System.out.println("++++++++++++++++++++++");
	}
}
