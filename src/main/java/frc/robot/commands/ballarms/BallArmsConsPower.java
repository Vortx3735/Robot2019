package frc.robot.commands.ballarms;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 *
 */

public class BallArmsConsPower extends Command {

	double power;

	public BallArmsConsPower(double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.power = power;
		requires(Robot.ballArms);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.ballArms.controller.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.ballArms.setMotorSpeed(power);
		System.out.println("Sending " + power + " percent output to arms");
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(Robot.oi.getArmsMove()) > 0.05;
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
