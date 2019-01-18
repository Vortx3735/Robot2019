package frc.robot.commands.drive.positions;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class ZeroYaw extends InstantCommand {

    public ZeroYaw() {
    	this.setRunWhenDisabled(true);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.navigation.zeroYaw();
    }

}
