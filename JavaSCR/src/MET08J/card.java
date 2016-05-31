package MET08J;

public class card {
	private final int number;

	public card(int number) {
		this.number = number;
	}

	public boolean equals(Object o) {
		if (!(o instanceof card)) {
			return false;
		}

		card c = (card) o;
		return c.number == number;
	}

	// Comply with MET09-J
	public int hashCode() {
		/* ... */
		return 0;
	}

}