import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.ArrayList;
import java.util.Enumeration;
public class ArduinoConnect implements SerialPortEventListener {

    public SerialPort serialPort;
    private static final String PORT_NAMES[] = {"COM4"};
    public static BufferedReader input;
    public static OutputStream output;
    public static final int TIME_OUT = 2000;
    public static final int DATA_RATE = 9600;
    private int a = 0;
    private char x;
    private int list;

    public ArduinoConnect(int list, ArrayList<ArrayList<Integer>> producten){
        this.list = list;

    }

    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));


            output = serialPort.getOutputStream();


            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                int in = input.read();
                //Check if ARDUINO gives P
                if(in == 112){
                    System.out.println("ARDUINO gave back " + (char) in);
                    //System.out.println();
                    //cFrame.getImage().setImage("WAIT_FOR_INPUT");
                    a++;
                    if(a == list){
                        x = (char) 'E';
                        System.out.println("All blocks have been pushed! Writing "+ x +" TO OUTPUT...");
                        try
                        {
                            output = serialPort.getOutputStream();
                            output.write(x);
                            System.out.println("Written to! Waiting for ARDUINO output...");
                        }
                        catch (IOException | NullPointerException nx){
                            System.out.println("Could not find COM port / An error has occured.");
                            //cFrame.getImage().setImage("ERROR");
                        }
                    }
                }
                // D - event.
                if(in == 100){
                    //this.shiftOut();
                    System.out.println("done ready for next");
                }
                //L
                if(in == 108){
                    //this.shiftOut();
                    System.out.println("lading losing");
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

    }

    public static synchronized void writeData(String data) {
        System.out.println("Sent: " + data);
        try {
            output.write(data.getBytes());
        } catch (Exception e) {
            System.out.println("could not write to port");
        }
    }
}