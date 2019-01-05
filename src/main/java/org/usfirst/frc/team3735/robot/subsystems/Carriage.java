package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Carriage extends Subsystem {

	VortxTalon carriageLeft;
	VortxTalon carriageRight;
	
	Solenoid solenoid;
	private boolean raised;
	
	private boolean gripping;
	
	public Carriage(){
		carriageLeft = new VortxTalon(RobotMap.Carriage.carriageLeft);
		carriageRight = new VortxTalon(RobotMap.Carriage.carraigeRight);
		
		solenoid = new Solenoid(RobotMap.Carriage.solenoid);
		
		raised = false;
	}
	
	public void setCarriageLeftCurrent(double speed){
		carriageLeft.set(speed);
	}
	
	public void setCarriageRightCurrent(double speed){
		carriageRight.set(speed);
	}
	
	public void setCarriageCurrent(double speed){
		setCarriageLeftCurrent(speed + Robot.oi.getCarriageMove());
		setCarriageRightCurrent(speed + Robot.oi.getCarriageMove());
	}
//	
//	public void setCarriageCurrentCheck(double speed){
//		if(getPower() > 20){
//			gripping = true; 
//			speed = 0;
//			setCarriageCurrent(speed);
//		}else{
//			gripping = false;
//			setCarriageCurrent(speed);
//		}
//		
//	}
	
	public double getPower(){
		return Math.abs(carriageLeft.getOutputCurrent() * carriageLeft.getMotorOutputVoltage());
	}
	
	public void raise(){
		solenoid.set(false);
		raised = true;
	}
	
	public void lower(){
		solenoid.set(true);
		raised = false;
	}
	
	public void switchSolenoid(){
		if(raised){
			lower();
		}else{
			raise();
		}
	}
	
	public void log(){
	}
	
	public void debugLog(){
		SmartDashboard.putNumber("Carriage Power", getPower());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CarriageSetRoller(0));
    }
}

