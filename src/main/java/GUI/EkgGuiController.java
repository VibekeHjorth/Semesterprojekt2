package GUI;

import Business.EkgObserver;
import Data.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

import java.util.Date;

public class EkgGuiController implements EkgObserver {
    EkgDataRecorder recorder= new DummyEkgRecorder();
    //EkgDataRecorder recorder = EKGApp.testMode ? new DummyEKGRecorder(): new RealEKGRecorder();

    // Kobler poly til vores FXML fil.
    @FXML
    public Polyline poly;
    @Override
    public void handle(EkgData ekgData) {
        // update UI on UI Thread
        Runnable task = new Runnable() {
            @Override
            public void run() {
                poly.getPoints().addAll(ekgData.getTime(),ekgData.getVoltage());

            }
        };
        Platform.runLater(task); // Alt kører på gui tråden - tasks til Gui sættes i kø

    }

    // Ved museklik. DataAccess klasse forbinder kode med database.
    public void startEkg(MouseEvent mouseEvent) {
        EkgDataAccess ekgDataAccess = new EkgDataAccess();
        EkgDTO ekg = ekgDataAccess.createEKG(1, new Date());


        // start recorder and tell it to notify this class - observer pattern
        recorder.setObserver(this);
        recorder.record();
    }

};
