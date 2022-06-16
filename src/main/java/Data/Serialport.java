package Data;
import Business.EkgObserver;
import com.fazecast.jSerialComm.*;
import java.io.*;
import java.util.List;

public class Serialport implements EkgDataRecorder {
    // Declare objects whithin class but out from methods.
    SerialPort serialPort; // fra bibliotek jSerialComm
    PrintWriter out;
    BufferedReader reader;
    private EkgObserver observer;

    // Method to open serialport
    public void open() {
        serialPort = SerialPort.getCommPort("COM3");
        System.out.println("Trying to open port");
        boolean portOpened = serialPort.openPort();// Open serial port
        serialPort.setComPortParameters(115200, 8, 1, 0);// Set params.
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        System.out.println("Port opened: " + portOpened + ". Waiting 5 seconds before starting output stream");

        // PrintWriter and getOutputStream() method to send data to Arduino
        //out = new PrintWriter(serialPort.getOutputStream()); // Helps writing to arduino object af printwriter i variablen out
        reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream())); // Helps read from arduinom
    }
    @Override
    public void record() {
        // Method to read data from Arduino
        public String readData() {
            List<int> resultList = new List<int>() {
            };
            while (true) {
                try {
                    if (serialPort.bytesAvailable() > 0) {
                        var inputText = reader.readLine();
                        int intResult=Integer. parseInt(inputText);
                        resultList = resultList.add(intResult);
                        System.out.println("Recieved data from arduino: " + inputText);
                    } else {
                        return resultList;
                    }
                } catch (IOException ex) {
                    System.out.println("IO exception");
                    return null;
                }

            }
        }}




    @Override
    public void setObserver(EkgObserver observer) {
        this.observer=observer;}

    // Closing serialport
    public void close() { serialPort.closePort();// Close serial port
    }
}

// Send values to Arduino
    /*public void send(String value) {

        // Printing full string with all values for testing
        //System.out.print(value);

        // Changed from println to print due to already '\n' in between values
        System.out.println("Sending data to arduino...");
        out.print(value);
        out.flush();
    }*/

// Waiting for "Ready" from arduino
    /*public void waitForReady() {

        while (true) {
            try {

                if (serialPort.bytesAvailable() > 0) {
                    var inputText = reader.readLine();

                    break;
                } else {
                    System.out.println("Arduino not ready yet :(  Waiting 1 sec");
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1000);
                }
            } catch (IOException ex) {
                System.out.println("IO exception");
            } catch (InterruptedException ex) {
                System.out.println("Interrupt exception");
            }
        }
    }*/
