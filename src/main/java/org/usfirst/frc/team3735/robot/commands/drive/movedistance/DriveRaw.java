package org.usfirst.frc.team3735.robot.commands.drive.movedistance;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveRaw extends VortxCommand {

	private double move;
    private double turn;

	public DriveRaw(double move, double turn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.move = move;
    	this.turn = turn;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setupDriveForSpeedControl();
    	super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	super.execute();
    	Robot.drive.normalDrive(move, turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.drive.normalDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
