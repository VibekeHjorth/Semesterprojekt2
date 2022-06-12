package GUI;

import Business.EkgObserver;
import Data.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

import java.util.Date;

public class EkgGuiController implements EkgObserver {
    EkgDataRecorder recorder = new DummyEkgRecorder();

    @FXML
    public Polyline poly;

    @Override
    public void handle(EkgData ekgData) {
        // update UI on UI Thread
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int cycle = 0;
                poly.getPoints().addAll(ekgData.getTime()-cycle*100,ekgData.getVoltage());
                if(ekgData.getTime()>100+100*cycle) {
                    poly.getPoints().clear();
                    cycle++;

                }
                //poly.getPoints().addAll(ekgData.getTime()-cycle*100,ekgData.getVoltage());
                //poly.getPoints().remove(0);

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
}
