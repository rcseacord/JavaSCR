package MET11J;

import java.io.Serializable;

class myKey implements Serializable {

	// Does not override hashCode()
	/*
	public int hashCode() {
		return 1;
	}
	 */

	public boolean equals(Object o) {
		if (!(o instanceof myKey)) {
			return false;
		}
		return true;
	}

}