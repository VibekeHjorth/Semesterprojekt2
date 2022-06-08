package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private static Connection connection;
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://mysql-db.caprover.diplomportal.dk:3306/s215845?" +
                        "user=s215845&password=sNFl6rNpr4W7X3BlFPUEb");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
