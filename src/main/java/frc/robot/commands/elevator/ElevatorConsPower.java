package frc.robot.commands.elevator;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ElevatorConsPower extends Command {

	double consPower;

	public ElevatorConsPower(double consPower) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.consPower = consPower;
		requires(Robot.elevator);
	}


	// Called just before this Command runs the first time
	protected void initialize() {

		Robot.elevator.controller.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.out.println("Giving cos power of .08");
        Robot.elevator.setPOutput(consPower);
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		System.out.println("elev joy is " + Robot.oi.getElevatorMove());
		return Robot.elevator.getPosition()<2 || (Math.abs(Robot.oi.getElevatorMove()) >= 0.03);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.setPOutput(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
