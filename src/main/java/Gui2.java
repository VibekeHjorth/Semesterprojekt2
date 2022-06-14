import Data.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Gui2 {
    Connection conn = SqlConnection.getConnection();
    try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in cpr:");
        String patientid = scanner.next();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM ekg JOIN patient WHERE ekg.patient_id=patient.id and patient.cpr=?");
        statement.setString(1, patientid);
        System.out.println("Connection established");
        conn.setAutoCommit(false);
        ResultSet show_tables = statement.executeQuery();
        System.out.println("Got a resultset with number of colums:");
        System.out.println(show_tables.getMetaData().getColumnCount());
        while (show_tables.next()){
            System.out.println("Column 1: " + show_tables.getString(1));
            System.out.println("Column 2: " + show_tables.getString(2));
            System.out.println("Column 3: " + show_tables.getString(3));



        }


    } catch (SQLException e) {
        e.printStackTrace();
    }

}

