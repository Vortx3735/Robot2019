package frc.robot.commands.elevator;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ElevatorMoveJoystick extends Command {

	public ElevatorMoveJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.controller.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if(Math.abs(Robot.oi.getElevatorTrim()) > .3) {
//			Robot.elevator.setElevatorLeftCurrent(Robot.oi.getElevatorMove()*Robot.elevator.getMultiplierSmartDashboard() + (-Robot.oi.getElevatorTrim() * Robot.elevator.getCorrectionMultiplierSmartDashboard()));
//			Robot.elevator.setElevatorRightCurrent( Robot.oi.getElevatorMove()*Robot.elevator.getMultiplierSmartDashboard() + (Robot.oi.getElevatorTrim() * Robot.elevator.getCorrectionMultiplierSmartDashboard()));
//		}else {
//			Robot.elevator.setElevatorMotorsCurrent(Robot.oi.getElevatorMove()*Robot.elevator.getMultiplierSmartDashboard());
//		}
			
		Robot.elevator.setPOutput(Robot.oi.getElevatorMove());


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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
