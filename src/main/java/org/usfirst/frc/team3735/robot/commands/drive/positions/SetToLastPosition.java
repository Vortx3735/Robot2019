package org.usfirst.frc.team3735.robot.commands.drive.positions;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.recording.DriveState;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetToLastPosition extends InstantCommand {

	String file;
	String filePath;
	Scanner sc;
	boolean needsLoading = false;
	
	public static StringSetting fileName = new StringSetting("Last Profile File", "defaultfile");

    public SetToLastPosition(String file) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.file = file;
    }
    
    public SetToLastPosition() {
    	needsLoading = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(needsLoading) {
    		file = fileName.getValue();
    	}
    	
		filePath = "/home/lvuser/"  + file + ".txt";
		//filePath = "C:\\Users\\Andrew\\Desktop\\"  + name + ".txt";

		try{
			sc = new Scanner(new File(filePath));
		}catch(Exception e){
			e.printStackTrace();
		}
		String line = "";
    	while(sc.hasNextLine()) {
    		line = sc.nextLine();
    	}
    	Robot.navigation.setPosition(DriveState.fromString(line).pos);
    	System.out.println("Position set to last of " + file);
    }


}
