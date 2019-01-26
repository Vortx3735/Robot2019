package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.NeutralMode;



public class BallIntake extends Subsystem {
	//TODO: use VorTXTalon and rename
	TalonSRX talIntake;

	Solenoid solIntake;
	int talval = 0;
	int solval = 0;
	public BallIntake()
	{
		talIntake = new TalonSRX(RobotMap.Intake.topMotor);
		//talIntake.setEnableBrake(true);
		talIntake.setNeutralMode(NeutralMode.Brake);
		
	}
	public void setIntake(double d)
	{
		talIntake.set(ControlMode.PercentOutput, d);
		
		

	}
	

	@Override
	protected void initDefaultCommand() {
		
	}
}

	//public Intake()
	// 	{
	// 		super();
	// 		l1 = new TalonSRX(RobotMap.Intake.leftMotor);
	// 		r1 = new TalonSRX(RobotMap.Intake.rightMotor);
	// 		top = new TalonSRX(RobotMap.Drive.topMotor);
	
	// 		leftSol = new Solenoid(RobotMap.Intake.leftSol);
	// 		rightSol = new Solenoid(RobotMap.Intake.rightSol);
	// 	}
		
	// //
		
	// 	public void setMotor(double d)
	// 	{
			
	// 		l1.set(ControlMode.PercentOutput, d);
	// 		r1.set(ControlMode.PercentOutput, -d);
	// 		top.set(ControlMode.PercentOutput, -d);
	// 	}
	// 	public void setSolonoid(boolean b)
	// 	{
	// 		leftSol.set(b);
	// 		rightSol.set(b);
	// 	}
	
	

