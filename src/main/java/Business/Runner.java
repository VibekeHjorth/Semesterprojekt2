package Business;
import Data.EkgDataImpl;
import Data.Serialport;
import com.fazecast.jSerialComm.SerialPort;

import java.util.Timer;

public class Runner {
    public static void main(String[] args) {
        EkgApp.run();

        // Serialport kode
        /*double timeStart;
        timeStart = System.currentTimeMillis();*/
        var sp = SerialPort.getCommPort(" ");
        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        var hasOpened= sp.openPort();
        if(!hasOpened){
            throw new IllegalStateException("Failed to open serial port");
        }
        // Tid ca. 13 i kildevideo
        Runtime.getRuntime().addShutdownHook(new Thread(()->{sp.closePort();}));

       /* var timer = new Timer();
        var timedSchedule = new Serialport(timeStart);

        sp.addDataListener(timedSchedule);

        System.out.println("Listen: "+ timedSchedule.getListeningEvents());
        timer.schedule(timedSchedule, 0, 1000);*/

    }
}
