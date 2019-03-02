package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//
/**
 *
 */
public class BallIntakeMotorSet extends Command {
	double speed = 0;
    public BallIntakeMotorSet(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		speed=d;
    	requires(Robot.ballIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.ballIntake.setIntake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.ballIntake.setIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
