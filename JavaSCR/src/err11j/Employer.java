package err11j;

class Employer {
	 
    final private EmployeeDAO dao;
 
    public Employer(EmployeeDAO dao) {
        this.dao = dao;
    }
 
    public static void findEmployee(String keyword) throws EmployeeException {
        try {
            EmployeeDAO.list();
        } catch (DAOException ex) {
            throw new EmployeeException("Can't find employee " + keyword); //$NON-NLS-1$
        }
    }
}


























// throw new EmployeeException("Can't find employee", ex);
