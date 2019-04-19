package frc.robot.commands.ballarms;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class BallArmsMoveJoystick extends Command {

	double speed;

	public BallArmsMoveJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.ballArms);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {		
		speed = Robot.oi.getArmsMove();
		speed *= .4;
		Robot.ballArms.setMotorSpeed(speed);
	}

	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.ballArms.setMotorSpeed(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
