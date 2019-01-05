package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCorrect extends Command {

	Func magnitude;
	
	/**
	 * 
	 * @param magnitude The magnitude to alternately move the motors.
	 * 					Positive values indicate the left moving up, and the right moving down
	 */
	public ElevatorCorrect(Func magnitude) {
		this.magnitude = magnitude;
		requires(Robot.elevator);
	}
	
	public ElevatorCorrect(double magnitude) {
		this(Func.getFunc(magnitude));
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.setLeftPOutput(magnitude.getValue());
		Robot.elevator.setRightPOutput(-magnitude.getValue());
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
