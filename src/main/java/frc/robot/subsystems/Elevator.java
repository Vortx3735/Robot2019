package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorMoveJoystick;
import frc.robot.util.PIDCtrl;
import frc.robot.util.hardware.VortxTalon;
import frc.robot.Constants;
import frc.robot.util.settings.PIDSetting;
import frc.robot.util.settings.Setting;

/**
 *
 */
public class Elevator extends Subsystem implements PIDSource, PIDOutput {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VortxTalon elevator;

	public PIDCtrl controller;

	public Setting consPower = new Setting("Elevator ConsPower", 0.0);	//.183 on the final


	public Elevator() {
//    	super("elevator","ELV");

		elevator = new VortxTalon(RobotMap.Elevator.elevatorMotors, "Elevator Motors");
		//TODO tuning on these values
		controller = new PIDCtrl(.15,.01,0,this,this,2);
		controller.setAbsoluteTolerance(.5);
		controller.setOutputRange(-.1, 7);
		//controller.sendToDash("Elevator PID");
		controller.disable();
		
		elevator.setInchesPerTick(Constants.Elevator.inchesPerTick);

		elevator.setNeutralMode(NeutralMode.Brake);

		elevator.initSensor(FeedbackDevice.QuadEncoder, false);

		elevator.setSensorPhase(true);
		
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
		//valueTable.getEntry("Elevator Encoder Inches").setNumber(getPosition());
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
