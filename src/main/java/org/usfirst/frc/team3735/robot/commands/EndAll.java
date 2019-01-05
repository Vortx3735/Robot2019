package org.usfirst.frc.team3735.robot.commands;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class EndAll extends Command {

    public EndAll() {
        requires(Robot.drive);
        requires(Robot.elevator);
        requires(Robot.pivot);
        requires(Robot.cubeIntake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
