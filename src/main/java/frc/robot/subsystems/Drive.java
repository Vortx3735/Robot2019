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
import frc.robot.settings.Dms;
import frc.robot.util.hardware.VortxTalon;
import frc.robot.util.settings.BooleanSetting;
import frc.robot.util.settings.Setting;


/**
 *
 */

public class Drive extends Subsystem {

	
	private VortxTalon l1;
	private VortxTalon r1;
	
	private static int iZone = 2;
	private static double maxV = 160;

	//for speed profiling
	public static final double slope = 0.00113174;
	public static final double minPct = 0.0944854;
	public static final double maxSpeed = (1-minPct)/slope; //about 800.11 rpm, 173 in/s
	
	
	
	private double leftAddTurn = 0;
	private double rightAddTurn = 0;
	private double visionAssist = 0;
	private double navxAssist = 0;
	
	public static Setting moveExponent = new Setting("Move Exponent", Constants.Drive.moveExponent, false);
	public static Setting turnExponent = new Setting("Turn Exponent", Constants.Drive.turnExponent, false);
	public static Setting scaledMaxMove = new Setting("Scaled Max Move", Constants.Drive.scaledMaxMove, false);
	public static Setting scaledMaxTurn = new Setting("Scaled Max Turn", Constants.Drive.scaledMaxTurn, false);
	
	private DDxDrive defCommand;
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
		l1 = new VortxTalon(RobotMap.Drive.leftTrain, "Left Drive");
		r1 = new VortxTalon(RobotMap.Drive.rightTrain, "Right Drive");
		l1.setInchesPerTick(Constants.Drive.InchesPerTick);
		r1.setInchesPerTick(Constants.Drive.InchesPerTick);
				
		//l1.setFMaxV(maxV);
		//r1.setFMaxV(maxV);
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
		l1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);	
		l1.setSelectedSensorPosition(0);
		l1.setSensorPhase(true);
		
		
		r1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		r1.setSelectedSensorPosition(0);
		r1.setSensorPhase(true);

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
	
	/**
	 * 
	 * @param ips	the desired speed, in inches per second
	 * @param dps	the desired turn rate, in degrees per second
	 */
	public void velocityDrive(double ips, double dps) {
    	double left = ips + (Math.toRadians(dps) * Dms.Bot.DriveBase.HALFWIDTH);
    	double right = ips - (Math.toRadians(dps) * Dms.Bot.DriveBase.HALFWIDTH);
    
		Robot.drive.setLeftRightPlus(Drive.speedToPercent(left), Drive.speedToPercent(right));
	}
	
	/**
	 * Limits the left and right speeds so that rotation is consistent
	 * across all move values. Modifies speed for consistent rotation.
	 * @param move
	 * @param rotate
	 */
	public void limitedDrive(double move, double rotate) {
		double left = move + getTurnAdditions() + rotate;
		double right = move - getTurnAdditions() - rotate;
		double leftSpeed;
		double rightSpeed;
//		if(left > 1) {
//			leftSpeed = 1;
//			rightSpeed = right - left + 1;
//		}else if(right > 1) {
//			rightSpeed = 1;
//			leftSpeed = left - right + 1;
//		}else if(left < -1) {
//			leftSpeed = -1;
//			rightSpeed = right - left - 1;
//		}else if(right < -1) {
//			rightSpeed = -1;
//			leftSpeed = left - right - 1;
//		}
		
		if(Math.abs(left) > 1) {
			leftSpeed = Math.signum(left);
			rightSpeed = right - left + Math.signum(left);
		}else if(Math.abs(right) > 1) {
			leftSpeed = left - right + Math.signum(right);
			rightSpeed = Math.signum(right);
		}else {
			leftSpeed = left;
			rightSpeed = right;
		}
		setLeftRight(leftSpeed, rightSpeed);
		
	}

	/**
	 * Drives in a circle with a specified radius
	 * @param radius
	 * @param move
	 */
	public void radialDrive(double radius, double move){
		double left;
		double right;
		if(radius > 0){
			radius = Math.abs(radius);
			left = move;
			right = move * (radius - Dms.Bot.HALFWIDTH)/
							  (radius + Dms.Bot.HALFWIDTH);
		}else{
			radius = Math.abs(radius);
			right = move;
			left = move * (radius - Dms.Bot.HALFWIDTH)/
				  			 (radius + Dms.Bot.HALFWIDTH);
		}
		setLeftRight(left, right);
	}
	
	public double getTurnAdditions() {
		return leftAddTurn + rightAddTurn + visionAssist + navxAssist;
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
	
	public void setNavxAssist(double error) {
		this.navxAssist = (error/180.0) * Navigation.navCo.getValue();
	}

	public void setJevoisAssist(double error) {
		
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
		return l1.getSelectedSensorPosition(0) * Constants.Drive.InchesPerTick *-1;
	}

	public double getRightPosition() {
		return r1.getSelectedSensorPosition(0) * Constants.Drive.InchesPerTick *-1;
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
		return l1.getSpeed();
	}
	
	public double getRightSpeed() {
		return r1.getSpeed();
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
    /**
     * 
     * @return the speed from the current on motors -- needs to be tested and made
     * This is for a backup if encoders fail
     */
    public double getSpeedFromCurrent() {
    	return 0;
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


	public double getLeftPositionInches() {
		return l1.getPosition();
	}
	public double getRightPositionInches() {
		return r1.getPosition();
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
		SmartDashboard.putNumber("Drive Left Ticks", getLeftPosition()/Constants.Drive.InchesPerTick);
		SmartDashboard.putNumber("Drive Right Ticks", getRightPosition()/Constants.Drive.InchesPerTick);
	}

	public void debugLog() {
		SmartDashboard.putNumber("Drive Left Position", getLeftPosition());
		SmartDashboard.putNumber("Drive Right Position", getRightPosition());

		SmartDashboard.putNumber("Drive Left Speed", getLeftSpeed());
		SmartDashboard.putNumber("Drive Right Speed", getRightSpeed());

		SmartDashboard.putNumber("Drive Left Get", l1.getMotorOutputPercent());
		SmartDashboard.putNumber("Drive Right Get", r1.getMotorOutputPercent());
		
		SmartDashboard.putNumber("Drive avg speed inches", getAverageSpeed());
	}

}

