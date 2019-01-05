package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorSetAndHold extends CommandGroup {
	
	public ElevatorSetAndHold(Func inches, Func power) {
		addSequential(new ElevatorSetPosLgs(inches, power));
		addSequential(new ElevatorSetPosPID(inches, false));
	}
	
}
