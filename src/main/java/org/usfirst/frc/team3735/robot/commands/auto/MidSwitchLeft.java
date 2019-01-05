package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosRaw;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.DelayedIntakeOut;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.triggers.HasPassedWaypoint;
import org.usfirst.frc.team3735.robot.triggers.LocationProximity;
import org.usfirst.frc.team3735.robot.util.profiling.Location;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MidSwitchLeft extends CommandGroup {

    public MidSwitchLeft(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft));	//initial cross to scale
    		addSequential(new AutoSwitchLineup(false));
//    		addSequential(new ElevatorSetPosRaw(15),4);
//    		addSequential(new CarriageSetRoller(-.7), 1);
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft5));	//backup from cube, go to scale
    		
    	}else {
//    		addParallel(new DelayedIntakeOut(.7));
    		Location loc = Waypoints.Pieces.switchLineupLeft;
//        	addSequential(new DriveExp(-.5, 0).addT(new LocationProximity(loc, 30)).addA(new NavxAssist(loc, true)));
    		addSequential(new AutoSwitchLineup(false));

    	}
    }
}
