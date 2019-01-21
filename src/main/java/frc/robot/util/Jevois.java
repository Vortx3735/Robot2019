package frc.robot.util;

import edu.wpi.first.wpilibj.SerialPort;

public class Jevois {
	
	//JeVois Camera Number
	static final int JEVOIS_CAM_NUMBER = 0;
	
	// Serial Port Constants 
	static final int BAUD_RATE = 115200;
	
	// Serial port used for getting target data from JeVois 
	SerialPort visionPort = null;

	// Values to be updating
	double cx;
	double cy;
	double area;
	
	//Thread rununing
	Thread nThread;

	public Jevois() {
		setUp();
	} 
    
	/*
	 * Connect to the JeVois Camera
	 */
	public void connectJeVois(){
		int retry_counter = 0;
		//Retry strategy to get this serial port open or switch to the other usb ports
		while(visionPort == null && retry_counter++ < 10){
			try {
				System.out.print("Creating JeVois SerialPort...");
				visionPort = new SerialPort(BAUD_RATE,SerialPort.Port.kUSB);
				System.out.println("SUCCESS!!");
			} catch (Exception e) {
				try {
					System.out.print("Creating JeVois SerialPort...");
					visionPort = new SerialPort(BAUD_RATE,SerialPort.Port.kUSB1);
					System.out.println("SUCCESS!!");
				} catch (Exception f) {
					try {
						System.out.print("Creating JeVois SerialPort...");
						visionPort = new SerialPort(BAUD_RATE,SerialPort.Port.kUSB2);
						System.out.println("SUCCESS!!");
					} catch (Exception g) {
						System.out.println("FAILED!!");
						System.out.println("Failed to connect to JeVois on any port!");
			            System.out.println("Retry " + Integer.toString(retry_counter));
					}
				}
			}
		}
	}

	/**
	 * Sends a command over serial to JeVois and returns immediately.
     * @param cmd String of the command to send (ex: "ping")
	 * @return number of bytes written
	 */
    private int sendCmd(String cmd){
	    int bytes;
        bytes = visionPort.writeString(cmd + "\n");
        System.out.println("wrote " +  bytes + "/" + (cmd.length()+1) + " bytes, cmd: " + cmd);
	    return bytes;
	};   

	public String getData() {
		return visionPort.readString();
	}

	//TODO: Figure out values to pass
	public void refresh() {
		String s = getData();
		String[] parts = s.split(" ");
		cx = Double.parseDouble(parts[0]);
	}

	public void sleep(int millis) {
		try {
			nThread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setUp() {
		try {
			connectJeVois();
			sendCmd("ping");
			System.out.println(getData());
	
			nThread = new Thread(new Runnable(){
			
				@Override
				public void run() {
					sleep(30);
					refresh();
				}
			});
			nThread.start();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Was not able to start jevois");
		}
		
	}

}
