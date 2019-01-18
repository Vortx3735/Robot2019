package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;

public class Jevois {
	
	//JeVois Camera Number
	static final int JEVOIS_CAM_NUMBER = 0;
	
	// Serial Port Constants 
	static final int BAUD_RATE = 115200;
	
	// MJPG Streaming Constants 
	static final int MJPG_STREAM_PORT = 5805;

	// JeVois Program Selection Constants - must align with JeVois .cfg files
	static final int MAPPING_WIDTH_PXL_1 = 320;
	static final int MAPPING_HEIGHT_PXL_1 = 240;
	static final int MAPPING_FRMRT_FPS_1 = 30;
	
	// Serial port used for getting target data from JeVois 
	SerialPort visionPort = null;
	
	// USBCam and server used for broadcasting a webstream of what is seen 
	UsbCamera visionCam = null;
	MjpegServer camServer = null;

	//TODO: have the jevois run in its own thread so it doesn't interfere with normal robot procedures.
	public Jevois() {
		//ConnectJeVois and Check if we are connected
		connectJeVois();
		sendCmd("ping");
		startCameraStream();
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
     * Open an Mjpeg streamer from the JeVois camera
     */
	public void startCameraStream(){
		try{
			System.out.print("Starting JeVois Cam Stream...");
			visionCam = new UsbCamera("VisionProcCam", JEVOIS_CAM_NUMBER);
			visionCam.setVideoMode(PixelFormat.kMJPEG, MAPPING_WIDTH_PXL_1, MAPPING_HEIGHT_PXL_1, MAPPING_FRMRT_FPS_1); 
			camServer = new MjpegServer("VisionCamServer", MJPG_STREAM_PORT);
			camServer.setSource(visionCam);
			System.out.println("Vision Cam Stream Opened!!");
		} catch (Exception e) {
			DriverStation.reportError("Cannot start camera stream from JeVois", false);
            e.printStackTrace();
		}
	}
	
	/**
	 * Cease the operation of the camera stream. Unknown if needed.
	 */
	public void stopCameraStream(){
			camServer.free();
			visionCam.free();
		
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

}
