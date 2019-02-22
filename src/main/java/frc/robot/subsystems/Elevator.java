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
import frc.robot.util.calc.DDxLimiter;
import frc.robot.util.calc.Range;
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
	private DDxLimiter limiter;
	// private Setting carriageSpeed;

	public Setting consPower = new Setting("Elevator ConsPower", 0.15);	//.183 on the final


	public Elevator() {
    super("elevator","ELV");
		limiter = new DDxLimiter(0, new Range(new Setting("Elevator DDx Limit", 4)));

		elevator = new VortxTalon(RobotMap.Elevator.elevatorMotors, "Elevator Left");
		
		controller = new PIDCtrl(.15,.01,0,this,this,2);
		controller.setAbsoluteTolerance(.5);
		controller.setOutputRange(-.7, 1);
		controller.sendToDash("Elevator PID");
		controller.disable();
		
//		elevatorLeft.setPIDSetting(new PIDSetting(90, .15, 145,0,.8,6));
//		elevatorRight.setPIDSetting(new PIDSetting(90, .15, 80,0,1));
		
		elevator.setTicksPerInch(Constants.Elevator.ticksPerInch);

//		elevatorLeft.putOnDash();
//		elevatorRight.putOnDash();
		
		elevator.setNeutralMode(NeutralMode.Brake);

		elevator.initSensor(FeedbackDevice.QuadEncoder, false);
		//elevatorRight.initSensor(FeedbackDevice.QuadEncoder, false);
		
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
		}else {
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
		SmartDashboard.putNumber("Elevator Position", getPosition());
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
	  setPOutputAdjusted(limiter.feed(output));
	}
	
	public void resetDDx() {
		limiter.reset(0);
	}
}
