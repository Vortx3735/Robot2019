package frc.robot.commands.ballarms;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class BallArmsConsPower extends Command {

	public BallArmsConsPower() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.controller.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
        Robot.elevator.setPOutput(0.00);
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.elevator.getPosition()<2;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.setPOutput(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
