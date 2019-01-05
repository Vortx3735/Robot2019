package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.Move;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.triggers.HasPassedWaypoint;
import org.usfirst.frc.team3735.robot.triggers.LocationProximity;
import org.usfirst.frc.team3735.robot.triggers.TimeOut;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SecondCubeSwitchGrab extends CommandGroup {

    public SecondCubeSwitchGrab() {
    	addSequential(new DriveExp(.6, -.4).addT(new HasMoved(50)));
    	addSequential(new TurnTo(Waypoints.Pieces.headCube),1.3);
    	addParallel(new DriveExp(.5, 0).addT(new LocationProximity(Waypoints.Pieces.headCube, 15)));
    	addSequential(new CubeSetRoller(-.6,-1).addT(new LocationProximity(Waypoints.Pieces.headCube, 7)).addA(new NavxAssist(Waypoints.Pieces.headCube, false)));
//    	addParallel(new DriveExp(-.5,0),.8);
    	addParallel(new Move(-40));
    	addSequential(new CubeSetRoller(-.6,-1),1);
    	addSequential(new CubeTransfer());
//    	addParallel(VortxCommand.asSequence(
//    			new CubeSetRoller(-.6,-1,2),
//    			new CubeTransfer()
//    	));
    	
    	
    	
    }
}
