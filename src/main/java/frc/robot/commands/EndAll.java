package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class EndAll extends Command {

    public EndAll() {
        requires(Robot.drive);
        requires(Robot.elevator);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.elevator.setPOutput(0.0);
    }

	@Override
	protected boolean isFinished() {
		return true;
	}

}
