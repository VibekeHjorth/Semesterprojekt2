package Data;
import Business.EkgObserver;
import com.fazecast.jSerialComm.*;
import java.util.TimerTask;


public class Serialport implements EkgDataRecorder {

    private EkgObserver observer;

    @Override
    public void record() {


    }

    @Override
    public void setObserver(EkgObserver observer) {
        this.observer=observer;
    }
    //Kilde: https://www.youtube.com/watch?v=9pbsasv2izk
    // extends TimerTask implements SerialPortDataListener
    /*private final double timeStart;
    // constructor
    public Serialport (double timeStart){
        this.timeStart = timeStart;
    }
    // Overrise run method in Timetask
    @Override
    public void run(){
        System.out.println("Time elapsed:  " + ( System.currentTimeMillis()-this.timeStart)+ "milliseconds");

    }*/


    /*@Override
    public int getListeningEvents() {

        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType()==SerialPort.LISTENING_EVENT_DATA_RECEIVED){
           System.out.println("Yes! The Arduino is alive!");
        }*/

    }

