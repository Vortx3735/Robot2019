package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ElevatorSetPos extends Command {
	double inches;
	private boolean finishes;
    public ElevatorSetPos(double inches, boolean finishes) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.finishes=finishes;
        this.inches = inches;

        requires(Robot.elevator);
    	
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.controller.setSetpoint(inches);
    	Robot.elevator.controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.controller.updateI();
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
