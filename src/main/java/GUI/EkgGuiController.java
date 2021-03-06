package GUI;

import Business.EkgObserver;
import Data.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import Data.DummyEkgRecorder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/** @Vibeke, @Amalie og @Asbjørn*/
public class EkgGuiController implements EkgObserver {
    EkgDataRecorder recorder =  new Serialport(); //new DummyEkgRecorder();
    private DataConsumer consumer;
    EkgDTO currentEkg;
    int currentEkgId = 1; //TODO: FIX so that it is related to a patient
    /** @Vibeke , @Asbjørn*/
    @FXML
    public Polyline poly;
    ArrayList<EkgSensorData> ekgDataBuffer = new ArrayList<EkgSensorData>();
    double cycle = 0;
    int amountOfDataPoints = 400;
    int pointsGenerated = 0;

    @Override
    public void handle(EkgSensorData ekgSensorData) {
        // update UI on UI Thread
        System.out.println(ekgSensorData.getVoltage());
        EkgValues ekgValues = new EkgValues(0,currentEkg.getId(),
                ekgSensorData.getVoltage(),ekgSensorData.getTime());
        consumer.enqueue(ekgValues);
        //This wakes up the consumer to save data
        if (consumer.getValuesCount() >= 10) {
            consumer.notifyOnEmpty();
        }
        //Den sætter data ind
        Runnable task = new Runnable() {
            @Override
            public void run() {
                pointsGenerated+= 1;

                var points = poly.getPoints();

                if (points.size() > amountOfDataPoints *2){
                    points.clear();
                    cycle += 1;
                }
                System.out.println(ekgSensorData.getVoltage());
                points.addAll((ekgSensorData.getTime()) - cycle * amountOfDataPoints, 1100-(ekgSensorData.getVoltage()));
            }
        };
        Platform.runLater(task); // Alt kører på gui tråden - tasks til Gui sættes i kø

//        ekgDataBuffer.add(ekgSensorData);
//
//        if(ekgDataBuffer.size() >= 10)
//        {
//            EkgDataAccess ekgDataAccess = new EkgDataAccess();
//            ekgDataAccess.createEkgValues(currentEkg.getId(), ekgDataBuffer );
//
//            ekgDataBuffer.clear();
//        }
    }



    // Ved museklik. DataAccess klasse forbinder kode med database.
    public void startEkg(MouseEvent mouseEvent) {
        EkgDataAccess ekgDataAccess = new EkgDataAccess();
        EkgDTO ekg = ekgDataAccess.createEKG(1, new Date());
        consumer = new DataConsumer();
        new Thread(consumer).start();

        // start recorder and tell it to notify this class - observer pattern
        recorder.setObserver(this);
        recorder.record();
        currentEkg = ekg;

        // Cbudtz kode til producer-consumer
        consumer = new DataConsumer();
        new Thread(consumer).start();
    }
/**@Amalie*/
    public void patientdata(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/gui2.fxml"));
        try {
            AnchorPane anchorPane= fxmlLoader.load();
            Stage loadStage= new Stage();
            loadStage.setScene(new Scene(anchorPane));
            loadStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**@Amalie*/
    public void loadPulsSide(ActionEvent actionEvent) { //
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/puls.fxml"));
        try {
            AnchorPane anchorPane= fxmlLoader.load();
            Stage loadStage= new Stage();
            loadStage.setScene(new Scene(anchorPane));
            loadStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
