package METOOJ;

final class ElementaryParticle {

	@SuppressWarnings("unused")
	private String fName;
	@SuppressWarnings("unused")
	private double fSpeed;
	
	/**
	 * @param aName
	 *   has content.
	 * @param aSpeed
	 *   is in the range 0 (inclusive) to 1 (inclusive), and is
	 *   expressed as a fraction of the speed of light. (The photon is
	 *   an example of an elementary particle which travels at this speed.)
	 * @exception IllegalArgumentException
	 *   if a param does not comply.
	 */
	public ElementaryParticle(String aName, double aSpeed) {
		if (!aName.trim().equals("")) {
			throw new IllegalArgumentException("Empty string");
		}
		if (aSpeed < 0.0 || aSpeed > 1.0) {
			throw new IllegalArgumentException("Speed not in range [0..1]: " + aSpeed);
		}
		fName = aName;
		fSpeed = aSpeed;
	}

	// ..other methods elided

}
