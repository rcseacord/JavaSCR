package err11j;

public class Program {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		Employer manager = new Employer(dao);

		try {
			manager.findEmployee("Spiff");
		} 
		catch (EmployeeException ex) {
			ex.printStackTrace();
		}
	}
}
