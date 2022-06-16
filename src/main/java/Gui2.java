import Data.*;
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
    public TextField cprField;

    public void buttonPressed(ActionEvent actionEvent) {
        //Fetch Data
        String cprtext = cprField.getText();
        List<EkgDTO> loadekg = new EkgDataAccess().loadekg(cpr);
        List<EkgValues>   values = new EkgDataAccess().loadEkgValues(ekg_id);

                List<Double> convertedValues = null;

        poly.getPoints().addAll(convertedValues);

        // @Override
        //    public void handle(EkgSensorData ekgSensorData) {
        //        // update UI on UI Thread
        //        EkgValues ekgValues = new EkgValues(0,currentEkgId,
        //                ekgSensorData.getVoltage(),ekgSensorData.getTime());
        //        consumer.enqueue(ekgValues);
        //        //This wakes up the consumer to save data
        //        consumer.notifyOnEmpty();
        //
        //        Runnable task = new Runnable() {
        //            @Override
        //            public void run() {
        //                pointsGenerated+= 1;
        //
        //                var points = poly.getPoints();
        //
        //                if (points.size() > amountOfDataPoints *2){
        //                    points.clear();
        //                    cycle += 1;
        //                }
        //
        //                points.addAll(ekgSensorData.getTime() - cycle * amountOfDataPoints, ekgSensorData.getVoltage());
        //            }
        //        };
        //        Platform.runLater(task); // Alt kører på gui tråden - tasks til Gui sættes i kø

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

