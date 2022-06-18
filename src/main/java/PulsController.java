import Data.EkgDTO;
import Data.EkgDataAccess;
import Data.EkgValues;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PulsController {
    @FXML
    public LineChart poly;
    //Opretter en polyline
    public TextField cprField;
    //opretter et cprfield
//opretter en knap, som har en metode, som henter data fra databasen.
    List<EkgValues> ekgvalues = new ArrayList<>();
    public void buttonPressed(ActionEvent actionEvent) {
        ekgvalues.clear();
        try {
            File myObj = new File("C:\\DTU\\DTU - Sundhedsteknologi\\2. Semester\\62450 Information technology 2 F22\\Semester Projekt 2\\Arduino og apparatur\\EKG dummy data HENRIKS\\201m (0).txt");
            Scanner myReader = new Scanner(myObj);
            long time = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                double voltage = Double.parseDouble(data);
                System.out.println(data);
                var ekgValue = new EkgValues();
                ekgValue.setEkg_Time(time);
                ekgValue.setVoltage(voltage *100);
                ekgvalues.add(ekgValue);
                time+= 1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Platform.runLater(()->{
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Number of Month");
            poly.setTitle("Stock Monitoring, 2010");
            XYChart.Series series = new XYChart.Series();
            series.setName("My portfolio");
            for (EkgValues value : ekgvalues){
                series.getData().add(new XYChart.Data(value.getEkg_Time(), value.getVoltage()));
            }
            poly.getData().add(series);
                //poly.axi
                //poly.getPoints().addAll(convertedValues)
        });

    }

    public void analyzePressed(ActionEvent actionEvent) {
        System.out.println("Analysis");
    }

}

