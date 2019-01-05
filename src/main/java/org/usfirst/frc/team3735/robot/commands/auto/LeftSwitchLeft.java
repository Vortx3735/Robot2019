package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.drive.ExpDrive;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchLeft extends CommandGroup {

    public LeftSwitchLeft(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.leftSwitchLeft));	//initial cross to scale
    		addSequential(new AutoSwitchLineup(false));
    		//    		addSequential(new SendProfile(Waypoints.Auto.leftSwitchLeft2));	//initial cross to scale
//    		addSequential(new SendProfile(Waypoints.Auto.leftSwitchLeft3));	//initial cross to scale
//    		addSequential(new SendProfile(Waypoints.Auto.leftSwitchLeft4));	//initial cross to scale
//    		addSequential(new SendProfile(Waypoints.Auto.leftSwitchLeft5));	//initial cross to scale

    	}else {
//    		addSequential(new DriveExp(-.8, -.4),.8);
    		addSequential(new AutoSwitchLineup(false));

    	}
    }
}
