package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorMoveJoystick;
import frc.robot.util.PIDCtrl;
import frc.robot.util.hardware.VortxTalon;
import frc.robot.Constants;
import frc.robot.util.settings.PIDSetting;

/**
 *
 */
public class Elevator extends Subsystem implements PIDSource, PIDOutput {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VortxTalon elevator;

	public PIDCtrl controller;

	public Elevator() {
//    	super("elevator","ELV");

		elevator = new VortxTalon(RobotMap.Elevator.elevatorMotors, "Elevator Motors");
		//TODO tuning on these values
		controller = new PIDCtrl(.103,.01,0,0,this,this,2);
		SmartDashboard.putData(controller);
		controller.setAbsoluteTolerance(1);
		controller.setOutputRange(-.35, .9);
		controller.sendToDash("Elevator PID");
		controller.disable();
		
		elevator.setInchesPerTick(Constants.Elevator.inchesPerTick);

		elevator.setNeutralMode(NeutralMode.Brake);

		elevator.initSensor(FeedbackDevice.QuadEncoder, true);//True on final


		resetEncoderPositions();
	}


	public void setupForPositionControl() {

	}

	public void resetEncoderPositions() {
		elevator.resetPosition();
	}

	public void setPOutput(double speed) {
		SmartDashboard.putNumber("Output Elevator", speed);
    	elevator.set(ControlMode.PercentOutput, speed);
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
		SmartDashboard.putNumber("Elevator Temp", getAverageTemp());
		SmartDashboard.putNumber("Elv Setpoint", controller.getSetpoint());
		//valueTable.getEntry("Elevator Encoder Inches").setNumber(getPosition());
	}
	
	public void debugLog() {
		elevator.debugLog();
	}

	public double getAverageTemp() {
		return (elevator.getTemperature()+elevator.followers[0].getTemperature())/2;
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
