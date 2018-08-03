package err11j;

import java.sql.*;

class DatabaseUtils {
    public static void executeQuery(String sql) throws SQLException {
        throw new SQLException("Oopsie!" + sql); //$NON-NLS-1$
    }
}