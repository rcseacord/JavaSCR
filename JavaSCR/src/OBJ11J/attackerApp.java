package OBJ11J;

//Invoke class and gain access to restricted features
public class attackerApp { 

	public static void main(String[] args) {
		interceptor i = interceptor.get(); // Stolen instance

		// Can store the stolen object even though this should have printed
		// "Invalid Object!"
		storage.store(i);

		// Now invoke any instance method of BankOperations class
		i.greet();

		userApp.main(args); // Invoke the original UserApp
	}
}