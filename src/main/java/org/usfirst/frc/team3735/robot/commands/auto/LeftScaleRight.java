package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleRight extends CommandGroup {

    public LeftScaleRight(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.leftScaleRight));	//initial cross to scale
    		addSequential(new AutoScaleLineup(true));

//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleRight2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleRight3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleRight4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleRight5));	//backup from cube, go to scale
    		
    	}else {
    		
    	}
    }
}
