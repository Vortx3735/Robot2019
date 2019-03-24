package frc.robot.commands.winch;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ConstantPushing extends Command {

    int count;

	public ConstantPushing() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.suck);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
        count++;
		if(count%6==0) {
            Robot.suck.setSolenoid(!Robot.suck.solenoid.get());
        }
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
		end();
	}
}
