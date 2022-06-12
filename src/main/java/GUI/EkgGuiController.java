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

import java.io.IOException;
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

    public void Loadnyside(ActionEvent actionEvent) {
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

    public void Loadnyside2(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/gui3.fxml"));
        try {
            AnchorPane anchorPane= fxmlLoader.load();
            Stage loadStage= new Stage();
            loadStage.setScene(new Scene(anchorPane));
            loadStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
