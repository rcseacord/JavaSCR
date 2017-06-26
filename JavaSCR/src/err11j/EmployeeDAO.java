package err11j;

import java.sql.*;

class EmployeeDAO {
	public static void list() throws DAOException {
		try {
			DatabaseUtils.executeQuery("SELECT"); //$NON-NLS-1$
		} catch (SQLException ex) {
			throw new DAOException("Error querying employee from database"); //$NON-NLS-1$
		}
	}
}


















































// 			throw new DAOException("Error querying employee from database", ex);