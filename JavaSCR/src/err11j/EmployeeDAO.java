package err11j;

import java.sql.*;

class EmployeeDAO {
	public void list() throws DAOException {
		try {
			DatabaseUtils.executeQuery("SELECT");
		} catch (SQLException ex) {
			throw new DAOException("Error querying employee from database");
		}
	}
}


















































// 			throw new DAOException("Error querying employee from database", ex);