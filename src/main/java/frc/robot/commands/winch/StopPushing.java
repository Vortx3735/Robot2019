package frc.robot.commands.winch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class StopPushing extends Command {

    public StopPushing() {
        requires(Robot.suck);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.suck.setSolenoid(false);
    }

	@Override
	protected boolean isFinished() {
		return true;
	}

}
