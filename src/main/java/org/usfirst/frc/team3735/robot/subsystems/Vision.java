package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.vision.VisionHandler;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends Subsystem {
	
	SerialPort jevoisPort;
	UsbCamera camera1;
	UsbCamera camera2;

public static int tick = 0;
	public double x=0;
	public double y=0;
	public double a=0;
	
	public static Setting dpp = new Setting("Vision dpp", 1);

	static final int BAUD_RATE = 115200;
	
	public Vision(){
		AxisCamera c;
		camera1 = CameraServer.getInstance().startAutomaticCapture(0); //trying to start it on second usb port
		camera1.setResolution(320, 240);
		
		camera2 = CameraServer.getInstance().startAutomaticCapture(1); //trying to start it on second usb port
		camera2.setResolution(320, 240);
		//camera2.setResolution(160, 120);


		//		try {
//			jevoisPort = new SerialPort(BAUD_RATE, SerialPort.Port.kUSB1);
//			System.out.println("Jevois coms created successfully");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		camera1 = CameraServer.getInstance().startAutomaticCapture(1); //trying to start it on second usb port
	}
	
	public String readString() {
		try {
			return jevoisPort.readString();
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public void sendString(String s) {
		try {
			jevoisPort.writeString(s);
			System.out.println("Sent " + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		if(tick % 50 == 0) {
			String cam = readString();
			if(!cam.equals("")) {
				try {
					String[] split = cam.split(" ");
					x = Double.parseDouble(split[0]);
					y = Double.parseDouble(split[1]);
					a = Double.parseDouble(split[2]);			
				} catch (Exception e) {
					System.out.println("Vision error on string: " + cam);
				}
				
			}
		}
		tick++;
 
	}

	
	public void setUpVision() {
		sendString("setmapping 0");
		sendString("setpar serlog All");
		sendString("setpar serout All");
		sendString("streamon");
	}
	
    public void log(){
    }
    
    public void debugLog() {
    	System.out.println("x: " + x + " y: " + y + " a: " + a);

    }

    public Double getRelativeCX(){
    	return x;
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}