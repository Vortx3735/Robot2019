package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class ToggleSickoMode extends InstantCommand {

    @Override
    protected void execute() {
        Robot.drive.sickoMode = !Robot.drive.sickoMode;

    }

}
