package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchRight extends CommandGroup {

    public RightSwitchRight(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.rightSwitchRight));	//initial cross to scale
    		addSequential(new AutoSwitchLineup(true));
//    		addSequential(new SendProfile(Waypoints.Auto.rightSwitchRight2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.rightSwitchRight3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.rightSwitchRight4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.rightSwitchRight5));	//backup from cube, go to scale
    		
    	}else {

    		addSequential(new AutoSwitchLineup(true));

    	}
    }
}
