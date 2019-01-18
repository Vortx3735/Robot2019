package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.calc.RollingAverage;

/**
 *
 */
public class RecordAverageSpeed extends Command {

	private RollingAverage roll;
	
    public RecordAverageSpeed() {
        roll = new RollingAverage(3){
        	@Override
        	public double get(){
        		return Robot.drive.getAverageSpeed();
        	}
        };
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	roll.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	roll.compute();
    	SmartDashboard.putNumber("Rolling Average Speed", roll.getAverage());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
