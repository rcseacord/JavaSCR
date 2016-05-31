package MET05J;

class subClass extends superClass {
	private String color = null;

	public subClass() {
		super();
		color = "Red";
	}

	public void doLogic() {
		System.out.println("subclass: color is: " + color);
		// ...
	}
}