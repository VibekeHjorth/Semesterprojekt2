import Business.EkgObserver;
import Data.EkgDataRecorder;
import Data.EkgSensorData;
import Data.Serialport;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import java.util.LinkedList;
import java.util.List;

public class SerielportTest implements EkgObserver {
    EkgDataRecorder recorder = new Serialport();
    List<EkgSensorData> dataList = new LinkedList<>();

    @Test
    public void testRecording(){
        recorder.setObserver(this);
        recorder.record();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Assertions.assertTrue(dataList.size() > 1);
    }
    @Override
    public void handle(EkgSensorData ekgSensorData) {this.dataList.add(ekgSensorData);}
}