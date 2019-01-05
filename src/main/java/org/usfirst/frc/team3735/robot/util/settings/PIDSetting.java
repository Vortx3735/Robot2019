package org.usfirst.frc.team3735.robot.util.settings;

import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDSetting extends PIDController{
	private Setting iZone;
	private Setting rampRate;
	
	public PIDSetting(double P, double I, double D){
		this(P, I, D, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F){
		this(P, I, D, F, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F, double rampRate){
		this(P, I, D, F, rampRate, 0);
	}
	

	
	public PIDSetting(double P, double I, double D, double F, double rampRate, double iZone){
		super(P, I, D, F, new BlankPID(), new BlankPID());
		
		this.iZone = new Setting("", iZone, false);
		this.rampRate = new Setting("", rampRate, false);
		
	}
	
	public PIDSetting(double P, double I, double D, double F, double rampRate, double iZone, PIDSource source, PIDOutput out){
		super(P, I, D, F, source, out);
		
		this.iZone = new Setting("", iZone, false);
		this.rampRate = new Setting("", rampRate, false);
		
	}

	public void sendToDash(String name){
		SmartDashboard.putData(name, this);
		
		this.iZone = new Setting(name + "iZone", iZone.getValue());
		this.rampRate = new Setting(name + "Ramp", rampRate.getValue());

	}

	public void setiZone(double iZone){
		this.iZone.setValue(iZone);
	}
	
	public void setRampRate(double rampRate) {
		this.rampRate.setValue(rampRate);
	}

	public double getiZone() {
		return iZone.getValue();
	}

	public double getRampRate() {
		return rampRate.getValue();
	}

	public void set(double P, double I, double D, double F, double rampRate, double iZone){
		setPID(P, I, D, F);
		this.iZone.setValue(iZone);
		this.rampRate.setValue(rampRate);
	}
	
	
	
}