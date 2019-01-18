package frc.robot.commands.drive.movedistance;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.settings.Constants;
import frc.robot.util.calc.VortxMath;

/**
 *
 */
public class DriveMoveDistancePIDNavx extends Command {
	
	private double deltaDistance;
	private double startDistanceLeft;
	private double startDistanceRight;
	private double endPositionLeft;
	private double endPositionRight;
	
	private double timeOnTarget = 0;
	private double finishTime = 0.5;
	
	private double p = .025;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	
	private double strongMultiplier = .6;	
	private double yawThreshold = .4;	//degrees
	private double targetYaw;
	private double weakMultiplier = 1.4;

    public DriveMoveDistancePIDNavx(double distance){
    	requires(Robot.drive);
    	this.deltaDistance = distance;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	startDistanceLeft = Robot.drive.getLeftPositionInches();
    	startDistanceRight = Robot.drive.getRightPositionInches();
    	endPositionLeft = startDistanceLeft + deltaDistance;
    	endPositionRight = startDistanceRight + deltaDistance;
    	
    	Robot.drive.setupForPositionControl();
    	Robot.drive.setPIDSettings(p,i,d,f);
    	
    	timeOnTarget = 0;
    	targetYaw = Robot.navigation.getYaw();
    	
    	System.out.println("Left distance " + startDistanceLeft);
    	System.out.println("Right distance " + startDistanceRight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.drive.setLeftRightDistance(endPositionLeft, endPositionRight);
		double distRight = endPositionRight-Robot.drive.getRightPositionInches();
		double distLeft = endPositionRight-Robot.drive.getLeftPositionInches();
		System.out.println("Left distance " + Robot.drive.getLeftPositionInches() + " need to go " + distLeft);
    	System.out.println("Right distance " + Robot.drive.getRightPositionInches() + " need to go" + distRight);
    	if(!VortxMath.isWithinThreshold(Robot.navigation.getYaw(), targetYaw, yawThreshold)){
    		if(Robot.navigation.getYaw() > targetYaw){
    			Robot.drive.setLeftPIDF(p*strongMultiplier, i, d, f);
    			Robot.drive.setRightPIDF(p*weakMultiplier, i, d, f);
    			SmartDashboard.putBoolean("Navx Right", true);
    			SmartDashboard.putBoolean("Navx Left", false);

    		}else{
    			Robot.drive.setLeftPIDF(p*weakMultiplier, i, d, f);
    			Robot.drive.setRightPIDF(p*strongMultiplier, i, d, f);
    			SmartDashboard.putBoolean("Navx Left", true);
    			SmartDashboard.putBoolean("Navx Right", false);

    		}
    	}else{
        	Robot.drive.setPIDSettings(p,i,d,f);
			SmartDashboard.putBoolean("Navx Left", false);
			SmartDashboard.putBoolean("Navx Right", false);
    	}
		Robot.drive.setLeftRightDistance(endPositionLeft, endPositionRight);
    	if(isOnTarget()){
    		timeOnTarget += .02;
    	}else{
    		timeOnTarget = 0;
    	}
    }
    
    private boolean isOnTarget(){
    	return 	VortxMath.isWithinThreshold(Robot.drive.getLeftPositionInches(),
										   	endPositionLeft,
										   	Constants.Drive.driveTolerance) &&
    			VortxMath.isWithinThreshold(Robot.drive.getLeftPositionInches(),
						   				   	endPositionRight,
						   				   	Constants.Drive.driveTolerance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setupDriveForSpeedControl();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}

