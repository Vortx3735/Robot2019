package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.hardware.VortxVictor;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private VortxVictor leftMotor;
	private VortxVictor rightMotor;
	
	private Solenoid solenoid;
	private Solenoid sol2;
	
	private boolean isGripping;
	
	public CubeIntake(){
		leftMotor = new VortxVictor(RobotMap.CubeIntake.leftMotor);
		rightMotor = new VortxVictor(RobotMap.CubeIntake.rightMotor);
		
		leftMotor.setNeutralMode(NeutralMode.Brake);
		rightMotor.setNeutralMode(NeutralMode.Brake);

		solenoid = new Solenoid(RobotMap.CubeIntake.solenoid);
		sol2 = new Solenoid(RobotMap.CubeIntake.solenoid2);
		
		
//		cubeIntakeSpeed = new Setting("Cube Intake Speed", Constants.CubeIntake.cubeIntakeSpeed);
		
		isGripping = false;

	}
	
	public void setLeftMotorCurrent(double speed){
		leftMotor.set(speed + Robot.oi.getIntakeMove());
	}
	
	public void setRightMotorCurrent(double speed){
		rightMotor.set(speed + Robot.oi.getIntakeMove());
	}
	
	public void setMotorsCurrent(double speed){
		setLeftMotorCurrent(speed);
		setRightMotorCurrent(speed);
	}
//	
//	public double getDashboardSpeed(){
//		return cubeIntakeSpeed.getValueFetched();
//	}
	
	
	
	public void grab(){
		solenoid.set(true);
		sol2.set(true);
		isGripping = true;
	}
	
	public void release(){
		solenoid.set(false);
		sol2.set(false);

		isGripping = false;
	}
	
	public void switchSolenoid(){
		if(isGripping){
			release();
		}else{
			grab();
		}	
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeSetRoller(0));
    }

	public void setLeftSol(boolean left) {
		solenoid.set(left);
		
	}	
	public void setRightSol(boolean left) {
		sol2.set(left);
		
	}
	
	public double getPower() {
		return .5 * (leftMotor.getPower() + rightMotor.getPower());
	}
}

