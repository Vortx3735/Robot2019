package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ReverseDrive extends InstantCommand {

    public ReverseDrive() {
    	this.setRunWhenDisabled(true);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.drive.reverseDrive();

    }

}