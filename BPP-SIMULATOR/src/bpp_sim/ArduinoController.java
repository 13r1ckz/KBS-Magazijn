package bpp_sim;

import bpp_sim.Application.ControllerFrame;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.Timer;

public class ArduinoController  implements SerialPortEventListener, ActionListener {
	private SerialPort serialPort;
        private final Timer tmr;
        private final int[] list;
        private int a = 0;
        private final ControllerFrame cFrame;
        
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
                        "/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM5", // Windows
	};
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 1000;
	private static final int DATA_RATE = 9600;
        
        /* constructor */
        public ArduinoController(int[] list, ArrayList<Product> producten, ArrayList<Doos> dozen) throws IOException
        {
            this.list = list;
            tmr = new Timer(5000, this);
            cFrame = new ControllerFrame(dozen,producten);
            cFrame.setVisible(true);
            this.initialize();
        }

        public void start(){
        }
        
        /* initialize the port */
	public void initialize()
        {
                System.setProperty("gnu.io.rxtx.SerialPorts", "COM5");

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
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
                        cFrame.getImage().setImage("ERROR");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/* close the port */
	public synchronized void close()
        {
            if (serialPort != null) {
                serialPort.removeEventListener();
                serialPort.close();
            }
	}

        /* serial event */
	public synchronized void serialEvent(SerialPortEvent oEvent)
        {
            
                if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    try {
                        int in = input.read();
                        //Check if ARDUINO gives R or L character back
                        if(in == 82 || in == 76){
                            System.out.println("ARDUINO gave back " + (char) in);
                            System.out.println();
                            cFrame.getImage().setImage("WAIT_FOR_INPUT");
                            a++;
                            if(a == list.length){
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
                            cFrame.getImage().setImage("ERROR");
                            }
                            }
                        }
                        // X - event.
                        if(in == 88){
                           this.shiftOut();
                        }
                        // O - event.
                        if(in == 79){
                            System.out.println("All OK!");
                            cFrame.getImage().setImage("OK");
                        }
                    } catch (IOException ex) {}
		}
	}
        
        char x;
        
        public void shiftOut(){
            x = (char) (list[a]+48);
            String dir = "null";
            if(x == '1'){
                cFrame.getImage().setImage("RIGHT");
                dir = "RIGHT";
            }
            if(x == '2'){
                cFrame.getImage().setImage("LEFT");
                dir = "LEFT";
            }
            System.out.println("Writing " + x + " (" + dir + ") " + " to Arduino...");
            try
            {
                output = serialPort.getOutputStream();
                output.write(x);
                System.out.println("Written to! Waiting for ARDUINO output...");
            }
            catch (IOException | NullPointerException nx){
                System.out.println("Could not find COM port / An error has occured.");
                cFrame.getImage().setImage("ERROR");
            }
        }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.shiftOut();
        tmr.stop();
    }
}
