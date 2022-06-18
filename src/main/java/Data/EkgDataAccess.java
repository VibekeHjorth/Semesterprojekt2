package Data;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void createEkgValues(List<EkgValues> ekgValues) {

        Connection conn = SqlConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ekg_values (voltage, ekg_time, ekg_id) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            // Kilde:https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date?fbclid=IwAR1j4y2K4OBh9y5g97npGGbUwzkZBiaHAW2UHuTfp4xKT3Q3Y5zfMVL9f54
            for (EkgValues i : ekgValues) {
                ps.setInt(1, (int) i.getVoltage());
                ps.setLong(2,  i.getEkg_Time()); //TODO: Make long in db;
                ps.setInt(3, i.getEkg_Id());
                ps.addBatch();

                //ps.clearParameters(); // WHY?
            }
            System.out.println("Inserted new data in db; Count = " + ekgValues.size());

            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<EkgDTO> loadekg(String cpr) {  //liste til at loade ekg, cpr er parameter)
        // loader cpr data fra
        try {
            Connection conn = SqlConnection.getConnection();  //får oprettet connection
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM ekg JOIN patient WHERE ekg.patient_id=patient.id and patient.cpr=?"); //joiner de to tabeller ekg og patient.
            statement.setString(1, cpr);  //finder cpr
            System.out.println("Connection established");
            conn.setAutoCommit(false);
            ResultSet show_tables = statement.executeQuery();   //execute query og giver data.
            System.out.println("Got a resultset with number of colums:");
            System.out.println(show_tables.getMetaData().getColumnCount()); //printer
            ArrayList<EkgDTO> ekgDTOS = new ArrayList<>();
            while (show_tables.next()) {
               EkgDTO patientdata= new EkgDTO();
                patientdata.setId(show_tables.getInt("id"));
                patientdata.setPerson_id(show_tables.getInt("patient_id"));
                patientdata.setStart_time(show_tables.getDate("start_time"));
                System.out.println("Column 1: " + show_tables.getString(1));
                System.out.println("Column 2: " + show_tables.getString(2));
                System.out.println("Column 3: " + show_tables.getString(3));
                ekgDTOS.add(patientdata);
            }
            return ekgDTOS;
        } catch (SQLException e) {
           e.printStackTrace();
       }

        return null;
    }
   public List<EkgValues> loadEkgValues(int ekgId) {

       try {
           Connection conn = SqlConnection.getConnection();
           PreparedStatement statement = conn.prepareStatement("SELECT * FROM ekg_values WHERE ekg_id=?");
           statement.setInt(1, ekgId);
           System.out.println("Connection established");
           conn.setAutoCommit(false);
           ResultSet show_tables = statement.executeQuery();
           //Henter alle statements og viser dem.
           System.out.println("Got a resultset with number of colums:");
           System.out.println(show_tables.getMetaData().getColumnCount());
           //Henter data fra tabeller
           ArrayList<EkgValues> ekgDTOS = new ArrayList<>();
           //Opretter en ny arrayliste
           while (show_tables.next()) {
               EkgValues ekgData = new EkgValues();
               //opretter et nyt objekt til klassen EkgValues.
               ekgData.setId(show_tables.getInt("id"));
               ekgData.setEkg_id(show_tables.getInt("ekg_id")); //henter værdien for kolonnen som en int
               ekgData.setVoltage(show_tables.getInt("voltage")); //henterværdien for kolonnen som en int
               ekgData.setEkg_Time(show_tables.getLong("ekg_time")); //henter værdien for kollonen som en long
               //henter kolonner under metoderne
               ekgDTOS.add(ekgData);
               //Tilføjer ekgdata til listen
               System.out.println("Column 1: " + show_tables.getString(1)); //Henter værdien for kolonnen i en række i objektet og returnerer det som en string.
               System.out.println("Column 2: " + show_tables.getString(2));
               System.out.println("Column 3: " + show_tables.getString(3));
               //printer kolonnerne og deres værdier ud.
           }
           return ekgDTOS;
           //returnerer listen
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }

}

