package Data;
import Business.EkgObserver;
import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Serialport implements EkgDataRecorder {
    // Declare objects whithin class but out from methods.
    SerialPort serialPort;
    PrintWriter out;
    BufferedReader reader;
    private EkgObserver observer;

    // Method to open serialport
    public void open() {
        serialPort = SerialPort.getCommPort("COM5");
        System.out.println("Trying to open port");
        boolean portOpened = serialPort.openPort();// Open serial port
        serialPort.setComPortParameters(38400, 8, 1, 0);// Set params.
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        System.out.println("Port opened: " + portOpened);

        // PrintWriter and getOutputStream() method to send data to Arduino
        //out = new PrintWriter(serialPort.getOutputStream()); // Helps writing to arduino object af printwriter i variablen out
        reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream())); // Helps read from arduino
    }
    @Override
    public void record() {
        open();

        // Method to read data from Arduino
        new Thread(new Runnable() {
            public void run() {
                try {
                    Long time = 0L;
                    while (true) {
                        Thread.sleep(10);
                        if(observer!=null){
                            if (serialPort.bytesAvailable() > 0) {
                                var inputText = reader.readLine();
                                System.out.println("Recieved data from arduino: " + inputText);
                                int intResult =0;

                                try{
                                    intResult=Integer.parseInt(inputText);
                                }  catch(NumberFormatException e) {
                                    //dirty
                                }

                                observer.handle(new EkgSensorDataImpl(intResult, time));
                                time+=1;
                        }
                    }}
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        }


    @Override
    public void setObserver(EkgObserver observer) {
        this.observer=observer;}

    // Closing serialport
    public void close() {
        serialPort.closePort();// Close serial port
    }
}
