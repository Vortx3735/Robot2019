package org.usfirst.frc.team3735.robot.commands.drive.movedistance;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveMoveDistancePID extends Command {
	
	private double deltaDistance;	
	private double startInchesLeft;
	private double startInchesRight;
	private double endInchesLeft;
	private double endInchesRight;
	
	private double timeOnTarget = 0;
	private double finishTime = 0.5;
	
	private double p = .1;
	private double i = 0;
	private double d = 0;
	private double f = 0;

    public DriveMoveDistancePID(double distance){
    	requires(Robot.drive);
    	this.deltaDistance = distance;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	startInchesLeft = Robot.drive.getLeftPositionInches();
    	startInchesRight = Robot.drive.getRightPositionInches();
    	endInchesLeft = startInchesLeft + deltaDistance;
    	endInchesRight = startInchesRight + deltaDistance;
    	
    	Robot.drive.setupForPositionControl();
    	Robot.drive.setPIDSettings(p,i,d,f);
    	
    	timeOnTarget = 0;
    	
    	System.out.println("Left distance " + startInchesLeft);
    	System.out.println("Right distance " + startInchesRight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.drive.setLeftRightDistance(endInchesLeft, endInchesRight);
    	if(isOnTarget()){
    		timeOnTarget += .02;
    	}else{
    		timeOnTarget = 0;
    	}
    	System.out.println(endInchesLeft-Robot.drive.getLeftPosition() +  " left togo");
    	System.out.println(Robot.drive.getCurrentPercent());
    }
    
    private boolean isOnTarget(){
    	return VortxMath.isWithinThreshold(Robot.drive.getLeftPositionInches(),
			   								endInchesLeft,
			   								Constants.Drive.driveTolerance) &&
    			VortxMath.isWithinThreshold(Robot.drive.getRightPositionInches(),
    										endInchesRight,
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

