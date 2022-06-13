package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EkgDataAccess {
    public EkgDTO createEKG(int person_id, Date ekgStart) {
        EkgDTO ekgDTO = new EkgDTO();
        // Oversætter Java dato til SQL dato
        var sqlDate = new java.sql.Date(ekgStart.getTime());
        Connection conn = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekg (patient_id, start_time) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            // Kilde:https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date?fbclid=IwAR1j4y2K4OBh9y5g97npGGbUwzkZBiaHAW2UHuTfp4xKT3Q3Y5zfMVL9f54
            preparedStatement.setInt(1, person_id);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.executeUpdate();
            // Vi vil gerne kende ID genererert med AUTO_INCREMENT i SQL
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                ekgDTO.setId(newId);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ekgDTO.setPerson_id(person_id);
        ekgDTO.setStart_time(sqlDate);
        return ekgDTO;
    }

    public void createEkgValues(int ekg_id, ArrayList<EkgData> ekg_Values) {

        Connection conn = SqlConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ekg_values (voltage, ekg_time, ekg_id) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            // Kilde:https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date?fbclid=IwAR1j4y2K4OBh9y5g97npGGbUwzkZBiaHAW2UHuTfp4xKT3Q3Y5zfMVL9f54
            for (EkgData i : ekg_Values) {
                ps.setInt(1, (int) i.getVoltage());
                ps.setInt(2, (int)i.getTime());
                ps.setInt(3, ekg_id);
                ps.addBatch();

                ps.clearParameters();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}

