package org.usfirst.frc.team3735.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3735.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	TalonSRX l1;
	TalonSRX r1;
	TalonSRX top;
	Solenoid leftSol;
	Solenoid rightSol;
	//
	public Intake()
	{
		super();
		l1 = new TalonSRX(RobotMap.Intake.leftMotor);
		r1 = new TalonSRX(RobotMap.Intake.rightMotor);
		top = new TalonSRX(RobotMap.Drive.topMotor);

		leftSol = new Solenoid(RobotMap.Intake.leftSol);
		rightSol = new Solenoid(RobotMap.Intake.rightSol);
	}
	
//
	
	public void setMotor(double d)
	{
		
		l1.set(ControlMode.PercentOutput, d);
		r1.set(ControlMode.PercentOutput, -d);
		top.set(ControlMode.PercentOutput, -d);
	}
	public void setSolonoid(boolean b)
	{
		leftSol.set(b);
		rightSol.set(b);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
