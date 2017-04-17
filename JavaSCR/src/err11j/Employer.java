package err11j;

class Employer {
	 
    final private EmployeeDAO dao;
 
    public Employer(EmployeeDAO dao) {
        this.dao = dao;
    }
 
    public void findEmployee(String keyword) throws EmployeeException {
        try {
            dao.list();
        } catch (DAOException ex) {
            throw new EmployeeException("Can't find employee " + keyword);
        }
    }
}


























// throw new EmployeeException("Can't find employee", ex);
