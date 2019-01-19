package frc.robot.commands.drive.positions;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class ResetPosition extends InstantCommand {

    public ResetPosition() {
        this.setRunWhenDisabled(true);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.navigation.resetPosition();

    }

}
