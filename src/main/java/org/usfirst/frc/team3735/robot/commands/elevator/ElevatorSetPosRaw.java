package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSetPosRaw extends VortxCommand {
double pos;
double dy;
	public ElevatorSetPosRaw(double pos) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.pos = pos;
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		dy = calc();
	}

	
	public double calc() {
		return pos - Robot.elevator.getPosition();
	}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if(Math.abs(Robot.oi.getElevatorTrim()) > .3) {
//			Robot.elevator.setElevatorLeftCurrent(Robot.oi.getElevatorMove()*Robot.elevator.getMultiplierSmartDashboard() + (-Robot.oi.getElevatorTrim() * Robot.elevator.getCorrectionMultiplierSmartDashboard()));
//			Robot.elevator.setElevatorRightCurrent( Robot.oi.getElevatorMove()*Robot.elevator.getMultiplierSmartDashboard() + (Robot.oi.getElevatorTrim() * Robot.elevator.getCorrectionMultiplierSmartDashboard()));
//		}else {
//			Robot.elevator.setElevatorMotorsCurrent(Robot.oi.getElevatorMove()*Robot.elevator.getMultiplierSmartDashboard());
//		}
			
		Robot.elevator.setPOutput(Math.signum(dy)* .4);


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.signum(dy) != Math.signum(calc());
				
	}

	// Called once after isFinished returns true
	protected void end() {		Robot.elevator.setPOutput(0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
