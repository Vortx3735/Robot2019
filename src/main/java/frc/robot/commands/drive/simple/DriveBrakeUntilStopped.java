package frc.robot.commands.drive.simple;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 *
 */
public class DriveBrakeUntilStopped extends CommandGroup {

    private static final double SPEED_THRESH = 1;

	public DriveBrakeUntilStopped() {
        addSequential(new DriveBrake(){
        	@Override
        	public boolean isFinished(){
        		return super.isFinished() || 
    				(Math.abs(Robot.drive.getLeftSpeed()) < SPEED_THRESH &&
    				 Math.abs(Robot.drive.getRightSpeed()) < SPEED_THRESH);
    				
        	}
        });
    }
}
