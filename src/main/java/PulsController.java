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

/** @Asbjørn, @Amalie, @Vibeke*/
// Særligt tak til IT software konsulent Kasper Hjorth Holdum for hjælp til denne løsning
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
            File myObj = new File("src/main/EKG data 201m (0)");
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
        var average = ekgvalues.stream().mapToDouble(s -> s.getVoltage()).average().getAsDouble();
        var max  = ekgvalues.stream().mapToDouble(s -> s.getVoltage()).max().getAsDouble();
        var betweenAverageAndMax = average + (max-average) * 0.5;

        double previousValue = -1000;
        double localMax = -1000;
        ArrayList<EkgValues> localMaximums = new ArrayList<>();
        boolean isClimbing = false;
        for(EkgValues value : ekgvalues){
            var voltage = value.getVoltage();
            if (voltage > betweenAverageAndMax){
                if (voltage > previousValue){
                    isClimbing = true;
                    localMax = voltage;
                }else{
                    // we're done climbing mountain
                    if (isClimbing){
                        localMaximums.add(value);
                        isClimbing = false;
                    }
                }
            }
            previousValue = voltage;
        }

        var first = localMaximums.get(0);
        var second = localMaximums.get(1);

        var firstTime = first.getEkg_Time();
        var secondTime = second.getEkg_Time();
        System.out.println("Analysis done. Found " + localMaximums.size() + " maximums");
        System.out.println("First maximum time and value: (" + first.getEkg_Time() + ", " + first.getVoltage() + ")");
        System.out.println("Second maximum time and value: (" + second.getEkg_Time() + ", " + second.getVoltage() + ")");
        var timeDiff = secondTime - firstTime;
        final var frequency = 400.0;
        var msPerDataPoint = 1000.0 / frequency;

        var totalMs = timeDiff * msPerDataPoint;
        System.out.println("Estimated time between first and second beat (ms): " + totalMs);

        var oneMinute = 1000*60;
        var beatsPerMinute = oneMinute / totalMs;

        System.out.println("Estimate pulse: " + beatsPerMinute);
    }
}

