package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorMoveJoystick;
import frc.robot.util.PIDCtrl;
import frc.robot.util.hardware.VortxTalon;
import frc.robot.Constants;
import frc.robot.util.settings.PIDSetting;
import frc.robot.util.settings.Setting;
import frc.robot.util.VortxSubsystem;

/**
 *
 */
public class Elevator extends VortxSubsystem implements PIDSource, PIDOutput {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VortxTalon elevator;
	
	public static double minDown = -.07;
	public static double minUp = .33;
	
	public static double bottom = 0;
	public static double switchHeight = 10;
	public static double top = 36;
	
	public static double transferHeight = 4.1;

	public PIDCtrl controller;
	// private Setting carriageSpeed;

	public Setting consPower = new Setting("Elevator ConsPower", 0.0);	//.183 on the final


	public Elevator() {
    	super("elevator","ELV");

		elevator = new VortxTalon(RobotMap.Elevator.elevatorMotors, "Elevator Motors");
		//TODO tuning on these values
		controller = new PIDCtrl(.15,.01,0,this,this,2);
		controller.setAbsoluteTolerance(.5);
		//TODO figure direction of motors
		controller.setOutputRange(-.7, 1);
		controller.sendToDash("Elevator PID");
		controller.disable();
		
		elevator.setTicksPerInch(Constants.Elevator.ticksPerInch);

		
		elevator.setNeutralMode(NeutralMode.Brake);

		elevator.initSensor(FeedbackDevice.QuadEncoder, false);
		
		resetEncoderPositions();
	}


	public void setupForPositionControl() {

	}

	public void resetEncoderPositions() {
		elevator.resetPosition();
	}

	public void setPOutput(double speed) {
    	elevator.set(ControlMode.PercentOutput, speed);
	}
	
	public void setPOutputAdjusted(double speed) {
//		System.out.print("Trying: " + speed + "\t");
//		double actual = speed;
		if((getPosition() < 1.5 ) && (speed == 0)) {
//			actual = 0;
			speed = 0;
		} else {
			speed+= consPower.getValue();
//			actual = speed + consPower.getValue();
		}

		setPOutput(speed);
	}

	public void setElevatorPIDSetting(PIDSetting setting) {
		elevator.setPIDSetting(setting);
	}

	public void setElevatorPosition(double position) {
    	elevator.set(ControlMode.Position, position);
	}

	public void setElevatorPosition(double position, PIDSetting setting) {
		setElevatorPIDSetting(setting);
		setElevatorPosition(position);
	}


	
	public double getPosition() {
		return elevator.getPosition();
	}


	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorMoveJoystick());
	}

	public void log() {
		elevator.log();
		//SmartDashboard.putNumber("Joysticks", Robot.oi.getElevatorMove());
		SmartDashboard.putNumber("Elevator Position Inches", getPosition());
	}
	
	public void debugLog() {
		elevator.debugLog();
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	
	}


	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}


	@Override
	public double pidGet() {
		return getPosition();
	}


	@Override
	public void pidWrite(double output) {
	  setPOutput(output);
	}
}
