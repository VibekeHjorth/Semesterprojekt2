/*import Business.EkgObserver;
import Data.EkgDataRecorder;
import Data.EkgSensorData;
import Data.Serialport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class ArduoinoRecorderTest implements EkgObserver {
    EkgDataRecorder recorder = (EkgDataRecorder) new Serialport();
    List<EkgSensorData> dataList = new LinkedList<>();

    @Test
    public void testRecording(){
        recorder.setObserver(this);
        recorder.record();
        //Wait and see if some data got it
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(dataList.size()>1);
    }

    @Override
    public void handle(EkgSensorData ekgSensorData) {
        this.dataList.add(ekgSensorData);
    }
}
*/