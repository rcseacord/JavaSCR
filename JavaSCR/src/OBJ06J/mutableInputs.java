package OBJ06J;

import java.net.HttpCookie;

public final class mutableInputs {

	void doLogic(HttpCookie cookie) {
		System.out.println("Do stuff");
	}

	// java.net.HttpCookie is mutable
	public void useMutableInput(HttpCookie cookie) {
		if (cookie == null) {
			throw new NullPointerException();
		}

		// Check whether cookie has expired
		if (cookie.hasExpired()) {
			// Cookie is no longer valid; handle condition by throwing an
			// exception
		}

		// Cookie may have expired since time of check
		doLogic(cookie);
	}
}