package org.usfirst.frc.team3735.robot.commands.drive.movedistance;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.util.calc.DDxLimiter;
import org.usfirst.frc.team3735.robot.util.calc.Range;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.settings.Func;


/**
 *
 */
public class MoveDDx extends VortxCommand {

	double startSpeed;
	double maxSpeed;
	HasMoved distHandler;
	
	double stoppingDist;
	
	DDxLimiter limiter;
	
	private boolean isAcc;
	private double targetSpeed;
	
    public MoveDDx(Func dist, double maxSpeed, double acc) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	limiter = new DDxLimiter(0, new Range(acc));
    	distHandler = new HasMoved(dist);
    	this.maxSpeed = Math.abs(maxSpeed);
    	requires(Robot.drive);
    }
    
    public MoveDDx(double dist, double maxSpeed, double acc) {
        this(Func.getFunc(dist), maxSpeed, acc);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	startSpeed = 0;
    	distHandler.initialize();
    	isAcc = true;
//    	maxSpeed = Math.abs(maxSpeed) * Math.signum(distHandler.distance());
    	
    	targetSpeed = Math.abs(maxSpeed) * Math.signum(distHandler.distance());
    	System.out.println("Distance to go: " + distHandler.distance());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//if we've reached cruise (we're acc)		or		we've reached half way	,	the dist travelled is stopping dist, stop acc
    	if(Math.abs(limiter.value) >= Math.abs(maxSpeed) && isAcc || Math.abs(distHandler.distanceToGo()) < Math.abs(distHandler.distanceTraveled())) {
    		stoppingDist = distHandler.distanceTraveled();
    		isAcc = false;
    	}
    	if(!isAcc) {
    		
    		//if we actually need to decelerate
    		if(Math.abs(distHandler.distanceToGo()) < Math.abs(stoppingDist) * .7) {
    			targetSpeed = Math.signum(distHandler.distance()) * .2;
    		}
    	}
    	Robot.drive.normalDrive(limiter.feed(targetSpeed), 0);
    	System.out.println("Target spd" + targetSpeed);
    	super.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distHandler.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
