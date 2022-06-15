package Business;

import Data.EkgSensorData;

public interface EkgObserver {
    void handle(EkgSensorData ekgSensorData);
}
