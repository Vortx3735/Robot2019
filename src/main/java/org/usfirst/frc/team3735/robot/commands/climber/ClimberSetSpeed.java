package org.usfirst.frc.team3735.robot.commands.climber;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberSetSpeed extends Command {

    private Func speed;

	public ClimberSetSpeed(Func spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cubeIntake);
    	this.speed = spd;
    }
	
	public ClimberSetSpeed(double spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(Func.getFunc(spd));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.setMotorCurrent((speed.getValue()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.setMotorCurrent(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
