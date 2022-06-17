import Data.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Gui2 {
    @FXML
    public Polyline poly;
    //Opretter en polyline
    public TextField cprField;
    //opretter et cprfield
//opretter en knap, som har en metode, som henter data fra databasen.
    public void buttonPressed(ActionEvent actionEvent) {
        //Fetch Data
        String cprtext = cprField.getText();
        //opretter et objekt, som henter teksten fra feltet.
        List<EkgDTO> loadekg = new EkgDataAccess().loadekg(cprtext);
        //TODO Make a listview for the patients EKG's - For now - just take the first

        List<EkgValues> ekgvalues = new EkgDataAccess().loadEkgValues(loadekg.get(0).getId());
//En liste med objekter, som loader elementer fra listen, henter id)
        List<Double> convertedValues = new ArrayList<>();  //opretter en ny liste til doubles
        for (EkgValues value : ekgvalues) {  //Tæller elementer i listen
            convertedValues.add((double) value.getEkg_Time());
            convertedValues.add(value.getVoltage());
        }
        Platform.runLater(()-> poly.getPoints().addAll(convertedValues));

    }

    /*Connection conn = SqlConnection.getConnection();
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
    } */

}

