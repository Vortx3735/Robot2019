package org.usfirst.frc.team3735.robot.commands.drive.recorder;

import java.util.Formatter;

import javax.tools.JavaFileObject;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.profiling.Position;
import org.usfirst.frc.team3735.robot.util.recording.DriveState;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecordProfile extends Command {

	public static StringSetting fileName = new StringSetting("Recording Profile File", "defaultfile");
	private static String filePath;
	private static Formatter formatter;
	

    public RecordProfile() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
		filePath = "/home/lvuser/"  + fileName.getValue() + ".csv";
		try{
			formatter = new Formatter(filePath);
			formatter.format("%s", DriveState.getCSVHeader());
			formatter.format("%s", System.getProperty("line.separator"));

		}catch(Exception e){
			e.printStackTrace();
		}
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//		formatter.format("%s", new DriveState(Robot.navigation.getPosition(), Robot.drive.getLeftPercent(), Robot.drive.getRightPercent()).toString());
		formatter.format("%s", new DriveState(Robot.navigation.getPosition(), Robot.drive.getLeftPercent(), Robot.drive.getRightPercent()).toCSV());

		formatter.format("%s", System.getProperty("line.separator"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    

    // Called once after isFinished returns true
    protected void end() {
		formatter.flush();
		formatter.close();
		
		SendProfile.loadCommand(fileName.getValue());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
