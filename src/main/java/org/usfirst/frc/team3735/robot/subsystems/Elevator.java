package org.usfirst.frc.team3735.robot.subsystems;

import java.nio.charset.Charset;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorMoveJoystick;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.PIDCtrl;
import org.usfirst.frc.team3735.robot.util.calc.DDxLimiter;
import org.usfirst.frc.team3735.robot.util.calc.Range;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem implements PIDSource, PIDOutput {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VortxTalon elevatorLeft;
	VortxTalon elevatorRight;
	
	public static double minDown = -.07;
	public static double minUp = .33;
	
	public static double bottom = 0;
	public static double switchHeight = 10;
	public static double top = 36;
	
	public static double transferHeight = 4.1;

	public PIDCtrl controller;
	private DDxLimiter limiter;
	// private Setting carriageSpeed;

	public Setting consPower = new Setting("Elevator ConsPower", 0.15);	//.183 on the final


	public Elevator() {
		limiter = new DDxLimiter(0, new Range(new Setting("Elevator DDx Limit", 4)));

		elevatorLeft = new VortxTalon(RobotMap.Elevator.elevatorLeft, "Elevator Left");
		elevatorRight = new VortxTalon(RobotMap.Elevator.elevatorRight, "Elevator Right");
		
		controller = new PIDCtrl(.15,.01,0,this,this,2);
		controller.setAbsoluteTolerance(.5);
		controller.setOutputRange(-.7, 1);
		controller.sendToDash("Elevator PID");
		controller.disable();
		
//		elevatorLeft.setPIDSetting(new PIDSetting(90, .15, 145,0,.8,6));
//		elevatorRight.setPIDSetting(new PIDSetting(90, .15, 80,0,1));
		
		elevatorLeft.setTicksPerInch(Constants.Elevator.ticksPerInch);
		elevatorRight.setTicksPerInch(Constants.Elevator.ticksPerInch);

//		elevatorLeft.putOnDash();
//		elevatorRight.putOnDash();
		
		elevatorLeft.setNeutralMode(NeutralMode.Brake);
		elevatorRight.setNeutralMode(NeutralMode.Brake);

		elevatorLeft.initSensor(FeedbackDevice.QuadEncoder, false);
		//elevatorRight.initSensor(FeedbackDevice.QuadEncoder, false);
		
		elevatorRight.follow(elevatorLeft);
		resetEncoderPositions();
	}


	public void setupForPositionControl() {

	}

	public void resetEncoderPositions() {
		elevatorLeft.resetPosition();
		elevatorRight.resetPosition();
	}

	public void setPOutput(double speed) {
		setLeftPOutput(speed);
		
//		System.out.println(speed);
//		setRightPOutput(speed);
	}
	
	public void setPOutputAdjusted(double speed) {
//		System.out.print("Trying: " + speed + "\t");
//		double actual = speed;
		if((getPosition() < 1.5 ) && (speed == 0)) {
//			actual = 0;
			speed = 0;
		}else {
			speed+= consPower.getValue();
//			actual = speed + consPower.getValue();
		}
		
		if(Robot.pivot.getPosition() > 100 && this.getPosition() < 15 && speed < consPower.getValue()) {
			speed = 0;
		}
		setPOutput(speed);
//		System.out.println(speed);
//		System.out.println("Sending: " + actual );

		
	}

	public void setLeftPOutput(double speed) {
		elevatorLeft.set(ControlMode.PercentOutput, speed);
//		System.out.println("Left Percent" + speed);
	}

	public void setRightPOutput(double speed) {
//		elevatorRight.set(ControlMode.PercentOutput, -speed);
//		if(speed < 0) {
//			elevatorRight.setInverted(false);
//
//		}else {
//			elevatorRight.setInverted(true);
//
//		}
//		elevatorRight.set(ControlMode.PercentOutput, speed);

		//System.out.println("Right Percent" + speed);
	}

	
	public void setElevatorLeftPosition(double position) {
		elevatorLeft.set(ControlMode.Position, position);
	}

	public void setElevatorRightPosition(double position) {
//		elevatorRight.set(ControlMode.Position, position);
	}
	

	public void setElevatorPIDSetting(PIDSetting setting) {
		elevatorLeft.setPIDSetting(setting);
		elevatorRight.setPIDSetting(setting);
	}

	public void setElevatorPosition(double position) {
		setElevatorLeftPosition(position);
		setElevatorRightPosition(position);
	}

	public void setElevatorPosition(double position, PIDSetting setting) {
		setElevatorPIDSetting(setting);
		setElevatorPosition(position);
	}


	
	public double getPosition() {
//		return (.5 * (elevatorLeft.getPosition() + elevatorRight.getPosition()));
		return elevatorLeft.getPosition();
	}


	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorMoveJoystick());
	}

	public void log() {
		elevatorLeft.log();
		elevatorRight.log();
		//SmartDashboard.putNumber("Joysticks", Robot.oi.getElevatorMove());
		SmartDashboard.putNumber("Elevator Position", getPosition());
	}
	
	public void debugLog() {
		elevatorLeft.debugLog();
		elevatorRight.debugLog();
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
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
		setPOutputAdjusted(limiter.feed(output));
	}
	
	public void resetDDx() {
		limiter.reset(0);
	}
}
