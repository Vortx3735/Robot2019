package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSetPosPID extends Command {
	Func inches;
	private boolean finishes;
    public ElevatorSetPosPID(double inches, boolean finishes) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(Func.getFunc(inches), finishes);
    	
    }
    
    public ElevatorSetPosPID(Func f, boolean finishes) {
    	this.inches = f;
    	this.finishes = finishes;
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.resetDDx();
    	Robot.elevator.controller.setSetpoint(inches.getValue());
    	Robot.elevator.controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.controller.updateI();
    	Robot.elevator.controller.setSetpoint(inches.getValue());
//    	Robot.elevator.setElevatorPosition(inches.getValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finishes && Robot.elevator.controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setPOutput(0);
    	Robot.elevator.controller.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
