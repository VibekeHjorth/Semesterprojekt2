package Business;

import Data.EkgSensorData;
// Denne klasse er genbrugt fra IT2-projektet
public interface EkgObserver {
    void handle(EkgSensorData ekgSensorData);
}
