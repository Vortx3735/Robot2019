package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.VortxSubsystem;
import frc.robot.util.hardware.VortxTalon;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.util.VortxSubsystem;


public class BallIntake extends VortxSubsystem {
	//TODO: use VorTXTalon and rename
	VortxTalon intakeMotor;

	Solenoid solIntake;
	int talval = 0;
	int solval = 0;
	public BallIntake() {
		super("ballIntake","BAL");
		intakeMotor = new VortxTalon(RobotMap.Intake.topMotor);
		intakeMotor.setNeutralMode(NeutralMode.Brake);
		
	}
	public void setIntake(double d)	{
		intakeMotor.set(ControlMode.PercentOutput, d);
	}
	
	public void log() {
		SmartDashboard.putNumber("BallIntakeSpeed", intakeMotor.get());
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
