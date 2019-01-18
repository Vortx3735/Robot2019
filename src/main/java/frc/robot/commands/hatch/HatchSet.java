package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//
/**
 *
 */
public class HatchSet extends Command {
	boolean b = true;
    public HatchSet(boolean b) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.b=b;
    	requires(Robot.hatch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.hatch.setSolenoid(b);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
