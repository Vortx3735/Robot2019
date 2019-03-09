package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ElevatorResetEncoder extends InstantCommand {

    public ElevatorResetEncoder() {
    	this.setRunWhenDisabled(true);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.elevator.resetEncoderPositions();;

    }

}