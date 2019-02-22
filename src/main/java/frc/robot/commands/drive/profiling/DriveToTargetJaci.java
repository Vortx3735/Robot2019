package frc.robot.commands.drive.profiling;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.calc.VortxMath;
import jaci.pathfinder.Waypoint;

/**
 *
 */
public class DriveToTargetJaci extends Command {
    
    Waypoint target;
    Waypoint current;
    double distanceToTarget;
    double angleToTarget;
    Command pathFollower;

    public double left;
    public double right;
    public double angle;
    public double desiredAngle;
    public double angleDifference;
    public double turn;

    public double originalAngleOffset;

	int count;
	
	public DriveToTargetJaci() {
        requires(Robot.drive);
		requires(Robot.navigation);
        requires(Robot.limelight);
    }

	

    // Called just before this Command runs the first time
    protected void initialize() {
        double x = Robot.navigation.getPosition().x;
        double y = Robot.navigation.getPosition().y;
        double theta = Robot.navigation.getPosition().yaw;
        current = new Waypoint(x, y, theta);
        target = getTarget();
        pathFollower = new PathFollower(new Waypoint[] {current, target});

        pathFollower.start();
    }

    protected Waypoint getTarget() {
        distanceToTarget = Robot.limelight.getDistance();
        angleToTarget = VortxMath.navLimit(Robot.navigation.getYaw()+Robot.limelight.getTx());
        double x = current.x + Math.cos(Math.toRadians(angleToTarget)) * distanceToTarget;
        double y = current.y + Math.sin(Math.toRadians(angleToTarget)) * distanceToTarget;
        
        return new Waypoint(x, y, angleToTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
       
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return pathFollower.isCompleted();
		//return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pathFollower.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}