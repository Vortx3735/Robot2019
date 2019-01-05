package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.settings.RobotMap.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorNoCurrent extends Command {

	public ElevatorNoCurrent() {
		requires(Robot.elevator);
	}
	

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.setPOutput(0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.setPOutput(0.0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
