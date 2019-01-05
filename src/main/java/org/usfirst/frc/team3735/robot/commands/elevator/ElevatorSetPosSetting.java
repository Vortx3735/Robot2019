package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSetPosSetting extends Command {
	Func inches;
	PIDSetting setting;
	
	static PIDSetting defaultPIDSetting = new PIDSetting(0,0,0,0,0,0);
	
	public ElevatorSetPosSetting(double inches){
		this(inches, defaultPIDSetting);
	}

    public ElevatorSetPosSetting(double inches, PIDSetting setting) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(new Func() {
    		@Override
    		public double getValue() {
    			return inches;
    		}
    	}, setting); 	
    }
    
    public ElevatorSetPosSetting(double inches, int level){
		this(inches, levelToPIDSetting(level));
	}
	
	public ElevatorSetPosSetting(Func f, int level){
		this(f, levelToPIDSetting(level));
	}
	
    public ElevatorSetPosSetting(Func f, PIDSetting setting) {
    	requires(Robot.elevator);
    	this.inches = f;
    	this.setting = setting;
    }
	
	public static PIDSetting levelToPIDSetting(int level){
		if(level == 1){
			//return PIDSetting1;
		}else if(level == 2){
			//return PIDSetting2;
		}
		return defaultPIDSetting;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setElevatorPIDSetting(setting);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setElevatorPosition(inches.getValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setPOutput(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
