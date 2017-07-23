package err11j;

import java.sql.*;

class EmployeeDAO {
	public static void list() throws DAOException {
		try {
			DatabaseUtils.executeQuery("SELECT"); 
		} catch (SQLException ex) {
			throw new DAOException("Error querying employee from database " + ex.getMessage()); 
		}
	}
}


















































// 			throw new DAOException("Error querying employee from database", ex);