package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeSetRoller extends VortxCommand {

    private Func left;
    private Func right;

    

	public CubeSetRoller(Func spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(spd, spd);
    }
	
	public CubeSetRoller(double spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(Func.getFunc(spd));
    }
	
	
	public CubeSetRoller(Func left, Func right) {
		this.left = left;
		this.right = right;
		requires(Robot.cubeIntake);
	}
	
	public CubeSetRoller() {
		this(-.42, -.7);
	}
	
	public CubeSetRoller(double left, double right) {
		this(Func.getFunc(left), Func.getFunc(right));
	}
	
	public CubeSetRoller(double left, double right, double time) {
		this(Func.getFunc(left), Func.getFunc(right));
		this.setTimeout(time);
	}


    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	super.execute();
		Robot.cubeIntake.setLeftMotorCurrent(left.getValue());
		Robot.cubeIntake.setRightMotorCurrent(right.getValue());
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.setMotorsCurrent(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
