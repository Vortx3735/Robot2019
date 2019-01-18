package org.usfirst.frc.team3735.robot.util;

import org.usfirst.frc.team3735.robot.util.settings.Func;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDCtrl extends PIDController{

	private Func iZone;
	private double actingI;
	
//	public PIDCtrl(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output, double period) {
//		super(Kp, 0, Kd, source, output, period);
//		actingI = Ki;
//	}
	public PIDCtrl(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output, double iz) {
		super(Kp, 0, Kd, source, output);
		actingI = Ki;
		iZone = Func.getFunc(iz);
	}
//	public PIDCtrl(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output, double period) {
//		super(Kp, 0, Kd, Kf, source, output, period);		
//		actingI = Ki;
//	}
	public PIDCtrl(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output, double iz) {
		super(Kp, 0, Kd, Kf, source, output);
		actingI = Ki;
		iZone = Func.getFunc(iz);
	}
	
	public void sendToDash(String name) {
		SmartDashboard.putData(name, this);
		iZone = new Setting(name + " IZone", iZone.getValue());
	}
	
	/*
	 * these methods set and update the I-zone for the controller

	 */
	public synchronized void updateI(){
		if(Math.abs(super.getError()) < iZone.getValue()){
			super.setPID(super.getP(), actingI, super.getD());
		}else{
			super.setPID(super.getP(), 0, super.getD());
		}
		
	}
	
	
	@Override
	public void setI(double i) {
		actingI = i;
	}
	
	
	/*
	 * these methods route to the controller, manipulating instead the acting I
	 */
	
	public synchronized void setPID(double p, double i, double d) {
		actingI = i;
		super.setPID(p, 0, d);	
	}

	
	public synchronized void setPID(double p, double i, double d, double f) {
		actingI = i;
		super.setPID(p, 0, d, f);
	}
	
//	public void sendToDash(String name) {
//		
//	}

}
