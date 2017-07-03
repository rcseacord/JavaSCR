package err11j;

class Program {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		Employer manager = new Employer(dao);

		try {
			manager.findEmployee("Spiff"); //$NON-NLS-1$
		} 
		catch (EmployeeException ex) {
			ex.printStackTrace();
		}
	}
}
