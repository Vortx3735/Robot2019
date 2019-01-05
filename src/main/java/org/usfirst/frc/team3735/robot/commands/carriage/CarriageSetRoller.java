package org.usfirst.frc.team3735.robot.commands.carriage;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CarriageSetRoller extends VortxCommand {

	Func speed;
    public CarriageSetRoller(Func speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.carriage);
    	this.speed = speed;
    }
    
    public CarriageSetRoller(double spd) {
    	this(Func.getFunc(spd));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.carriage.setCarriageCurrent(speed.getValue());
    	//System.out.println("Power: " + Robot.carriage.getPower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.carriage.setCarriageCurrent(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
