package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DDxDrive;
import frc.robot.Constants;
import frc.robot.util.hardware.VortxTalon;
import frc.robot.util.settings.BooleanSetting;
import frc.robot.util.settings.Setting;

/**
 *
 */

public class Drive  extends Subsystem {

	
	private VortxTalon l1;
	private VortxTalon r1;
	
	private static int iZone = 2;
	private static double maxV = 160;

	//for controllers
	public static final double minPct = 0.0944854;
	
	
	
	private double leftAddTurn = 0;
	private double rightAddTurn = 0;
	private double navxAssist = 0;
	private double visionMoveAssist = 0;
	private double visionTurnAssist = 0;
	
	public static Setting moveExponent = new Setting("Move Exponent", Constants.Drive.moveExponent, false);
	public static Setting turnExponent = new Setting("Turn Exponent", Constants.Drive.turnExponent, false);
	public static Setting scaledMaxMove = new Setting("Scaled Max Move", Constants.Drive.scaledMaxMove, false);
	public static Setting scaledMaxTurn = new Setting("Scaled Max Turn", Constants.Drive.scaledMaxTurn, false);
	
	public static BooleanSetting brakeEnabled = new BooleanSetting("Brake Mode On", false, false){
		@Override
		public void valueChanged(boolean val) {
			if(Robot.drive != null) {
				Robot.drive.setEnableBrake(val);
				System.out.println("Brake mode = " + val);

			}
		}
	};


	public Drive() {
		//super("drive","DRV");
		l1 = new VortxTalon(RobotMap.Drive.leftTrain, "Left Drive");
		r1 = new VortxTalon(RobotMap.Drive.rightTrain, "Right Drive");
		l1.setInchesPerTick(Constants.Drive.InchesPerTick);
		r1.setInchesPerTick(Constants.Drive.InchesPerTick);

		brakeEnabled.setIsListening(true);
		initSensors();
		setEnableBrake(true);
	}

	/*******************************
	 * Default Command For Driving
	 *******************************/
	public void initDefaultCommand() {
		setDefaultCommand(new DDxDrive());
	}

	/*******************************
	 * Setups for Position and Speed
	 *******************************/
	public void setupForPositionControl() {
		l1.configAllowableClosedloopError(0, 0, 0);
		l1.config_IntegralZone(0, iZone, 0);
		
		//slot, value, timeout
		r1.configAllowableClosedloopError(0, 0, 0);
		r1.config_IntegralZone(0, iZone, 0);

		setEnableBrake(true);		
	}

	/*******************************
	 * Speed Control Setup
	 *******************************/
	public void setupDriveForSpeedControl() {
		//setEnableBrake(false);

		this.setNavxAssist(0);
	}



	public void initSensors() {
		l1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);	
		l1.setSelectedSensorPosition(0);
		//l1.setSensorPhase(true);
		
		
		r1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
		r1.setSelectedSensorPosition(0);
		//r1.setSensorPhase(true);

	}

	public void zeroSensors() {
		l1.setSelectedSensorPosition(0);
		r1.setSelectedSensorPosition(0);
	}
	
	/*********************************
	 * Configuring left and right PID Peak Voltages
	 */
	public void setLeftPeakVoltage(double vol){
		//l1.configPeakOutputVoltage(vol, -vol);
		l1.configPeakOutputForward(vol, 0);
		l1.configPeakOutputReverse(-vol, 0);

	}

	public void setRightPeakVoltage(double vol){
		//r1.configPeakOutputVoltage(vol, -vol);
		r1.configPeakOutputForward(vol, 0);
		r1.configPeakOutputReverse(-vol, 0);
	}

	public void resetEncodersPositions(){
		l1.resetPosition();
		r1.resetPosition();

	}
	
	/*******************************
	 * Drive Functions
	 *******************************/
	
	/**
	 * Standard arcade drive from wpi's RobotDrive class
	 * @param move
	 * @param rotate
	 */
	public void arcadeDrive(double move, double rotate) {
		//copied from RobotDrive class. essentially lowers the speed of one motor first, rather than increases
		//one and decreases the other at the same time.
		
		double leftMotorSpeed;
		double rightMotorSpeed;
		
		double moveValue = move;
		double rotateValue = rotate + getTurnAdditions();
	    if (moveValue > 0.0) {
	        if (rotateValue < 0.0) {
	          leftMotorSpeed = moveValue + rotateValue;
	          rightMotorSpeed = Math.max(moveValue, -rotateValue);
	        } else {
	          leftMotorSpeed = Math.max(moveValue, rotateValue);
	          rightMotorSpeed = moveValue - rotateValue;
	        }
	      } else {
	        if (rotateValue < 0.0) {
	          leftMotorSpeed = -Math.max(-moveValue, -rotateValue);
	          rightMotorSpeed = moveValue - rotateValue;
	        } else {
	          leftMotorSpeed = moveValue + rotateValue;
	          rightMotorSpeed = -Math.max(-moveValue, rotateValue);
	        }
	      }
	      setLeftRight(leftMotorSpeed, rightMotorSpeed);
	}
	
	
	public void normalDrive(double move, double rotate){
		double rotateValue = rotate + getTurnAdditions();
		setLeftRight(move + rotateValue, move - rotateValue);
	}

	public void arcadeDrivePlus(double move, double rotate) {
		move += visionMoveAssist;
		rotate += getTurnAdditions();
		arcadeDrive(move, rotate);
	}
		
	
	public double getTurnAdditions() {
		return leftAddTurn + rightAddTurn + visionTurnAssist;// + visionAssist + navxAssist;
	}

	/*******************************
	 * Additive setters
	 *******************************/
	public void setLeftTurn(double turn){
    	leftAddTurn = turn;
    }
    public void setRightTurn(double turn){
    	rightAddTurn = turn;
    }
	
	public void setMoveVisionAssist(double move) {
		visionMoveAssist = move;
	}

	public void setTurnVisionAssist(double turn) {
		visionTurnAssist = turn;
	}

	public void setNavxAssist(double error) {
		this.navxAssist = (error/180.0) * Navigation.navCo.getValue();
	}

	
	/*******************************
	 * Brake Mode
	 *******************************/
	public void setEnableBrake(boolean b) {
		l1.setNeutralMode((b)? NeutralMode.Brake : NeutralMode.Coast);
		r1.setNeutralMode((b)? NeutralMode.Brake : NeutralMode.Coast);
	}
	
	public double getLeftError(){
		return l1.getClosedLoopError(0) * Constants.Drive.InchesPerTick ;
	}

	public double getRightError(){
		return r1.getClosedLoopError(0)* Constants.Drive.InchesPerTick ;
	}
	/*********************************
	 * Left and Right position getters
	 *********************************/

	public double getLeftPosition() {
		return l1.getSelectedSensorPosition(0) * Constants.Drive.InchesPerTick;
	}

	public double getRightPosition() {
		return r1.getSelectedSensorPosition(0) * Constants.Drive.InchesPerTick;
	}

	public double getLeftTicks() {
		return l1.getSelectedSensorPosition(0);
	}

	public double getRightTicks() {
		return r1.getSelectedSensorPosition(0);
	}
	
	/*
	 * 	The return value is in units per 100ms for all sensor types. 
	 * Sensor must be selected using 
	 * configSelectedFeedbackSensor()/ Multiply by (600/SensorUnitsPerRotation) to convert into RPM.
	 *
	 */

	
	public double getLeftSpeed() {
		return leftVelocity;
	}
	
	public double getRightSpeed() {
		return rightVelocity;
	}
	
	public double getAverageSpeed() {
		return .5 * (getLeftSpeed() + getRightSpeed());
	}

	public void setLeftRight(double left, double right) {
		l1.set(ControlMode.PercentOutput, left);
		r1.set(ControlMode.PercentOutput, right);
		//		System.out.println("Left: " + left + "Right: " + right);
		
	}
	
	public void setLeftRightPlus(double left, double right) {
		l1.set(left + getTurnAdditions());
		r1.set(right - getTurnAdditions());
	}
	
	public double getLeftPercent() {
		return l1.getMotorOutputPercent();
	}
	public double getRightPercent() {
		return r1.getMotorOutputPercent();
	}
	
	public double getTurn() {
		return .5 * (getLeftPercent() - getRightPercent());
	}
	
	public double getMove() {
		return .5 * (getLeftPercent() + getRightPercent());
	}
	

	/**
     * 
     * @param spd	the target speed in inches per second
     * @return	the percent, which converts spd into normal getspeed units, and then
     * 			compensates for the deadzone using gathered data
     */
    public static double speedToPercent(double spd){
//    	double speed = Math.abs(spd) * (0.1) / Constants.Drive.InchesPerTick;
//    	return Math.copySign(slope*speed + minPct, spd);
    	return spd / 104.0;
    }
    public static double percentToSpeed(double pct){
//    	return Math.copySign((pct - minPct) / slope, pct) * Constants.Drive.InchesPerTick/60.0;
    	return maxV * pct;
    }
    /**
     * @param 	percent [0,1] of the max speed to go
     * @return	the adjusted number to send to motors
     */
    public static double handleDeadband(double percent) {
    	return percent - (minPct * percent) + minPct;
    }
    public static double invHandleDeadband(double inv) {
    	return (inv-minPct)/(1-minPct);
    }
    
    /**
     * 
     * @return the percentage of the max speed that the robot is going at
     */
    public double getCurrentPercentSpeed() {
    	return invHandleDeadband(speedToPercent(getAverageSpeed()));
    }
    
    /**
     * 
     * @return	the percentVBus to send to the motors to maintain the current speed
     */
    public double getCurrentPercent() {
    	return speedToPercent(getAverageSpeed());
    }
    
    public void setLeftVelocity(double v) {
    	l1.set(ControlMode.Velocity, v);
    }
    public void setRightVelocity(double v) {
    	r1.set(ControlMode.Velocity, v);
    }
    
    /******************************************
     * PID driving 
     */
    
    public void setPIDSettings(double kp, double ki, double kd, double kf){
		l1.setPIDF(kp, ki, kd, kf);
		r1.setPIDF(kp, ki, kd, kf);
	}
    
    public void setLeftPIDF(double kp, double ki, double kd, double kf){
    	l1.setPIDF(kp, ki, kd, kf);
	}
	public void setRightPIDF(double kp, double ki, double kd, double kf){
		r1.setPIDF(kp, ki, kd, kf);
	}
	
	public void setLeftRightDistance(double left, double right) {
		l1.set(ControlMode.Position, left); //OneRotationInches //left / (Constants.Drive.InchesPerRotation 
		r1.set(ControlMode.Position, right);
	}

	
	/******************************************
	 * The Logs
	 ******************************************/
	public void log() {
		SmartDashboard.putNumber("Drive Left Position", getLeftPosition());
		SmartDashboard.putNumber("Drive Right Position", getRightPosition());
		calcSpeeds();
	}

	public void debugLog() {
		SmartDashboard.putNumber("Drive Left Ticks", getLeftTicks());
		SmartDashboard.putNumber("Drive Right Ticks", getRightTicks());

		SmartDashboard.putNumber("Drive Left Speed", getLeftSpeed());
		SmartDashboard.putNumber("Drive Right Speed", getRightSpeed());

		SmartDashboard.putNumber("Drive Left P Output", l1.getMotorOutputPercent());
		SmartDashboard.putNumber("Drive Right P Output", r1.getMotorOutputPercent());
		
		SmartDashboard.putNumber("Drive avg speed inches", getAverageSpeed());
	}

	double pastLeftInches;
	double pastRightInches;	

	double leftVelocity;
	double rightVelocity;


	public void calcSpeeds() {
			double leftInches = l1.getSelectedSensorPosition()*Constants.Drive.InchesPerTick;
			double rightInches = r1.getSelectedSensorPosition()*Constants.Drive.InchesPerTick;

			leftVelocity = (leftInches - pastLeftInches)/(Constants.dt);
			rightVelocity = (rightInches - pastRightInches)/(Constants.dt);

			pastRightInches = rightInches;
			pastLeftInches = leftInches;
	
		
		
	}
}

