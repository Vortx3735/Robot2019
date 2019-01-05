package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class PivotReset extends VortxCommand {

    public PivotReset() {
        requires(Robot.pivot);
    }


	@Override
	protected void initialize() {
		super.initialize();
		Robot.pivot.controller.disable();
	}


	@Override
	protected void execute() {
		Robot.pivot.setPOutput(-.5);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.pivot.resetEncoderPositions();
	}



	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}




}
