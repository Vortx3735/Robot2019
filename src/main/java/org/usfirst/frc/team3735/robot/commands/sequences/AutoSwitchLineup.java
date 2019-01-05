package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.EndAll;
import org.usfirst.frc.team3735.robot.commands.Wait;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageLower;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotSetPID;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveRaw;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.Bumped;
import org.usfirst.frc.team3735.robot.triggers.CarriageOverload;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.util.choosers.Side;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchLineup extends CommandGroup {

    public AutoSwitchLineup(boolean right) {
    	System.out.println("Auto Switch Lineup called");
    	Location target = (right) ? Waypoints.Pieces.switchRight : Waypoints.Pieces.switchLeft;
    	
//    	addSequential(new TurnTo(target, true),2);
    	addSequential(new PivotSetPID(0, true),1);
//    	addParallel(VortxCommand.asSequence(
////			new ElevatorSetPosDDx(Func.getFunc(Elevator.switchHeight), Func.getFunc(.7), Func.getFunc(.03)),
//			new ElevatorSetPosPID(Elevator.switchHeight, false)
//		), 2);
    	
		addParallel(new ElevatorSetPosPID(Elevator.switchHeight, false));

    	addSequential(new CarriageLower());
    	addSequential(new DriveExp(-.6, 0));//.addA(new NavxAssist(target, true)).addT(new Bumped(2)),2.5);
    	
    	double hugtime = 1;
    	
    	addParallel(new DriveRaw(-.3,0),hugtime);
    	addSequential(new Wait(.9));
    	addSequential(new CarriageSetRoller(-.6), hugtime);
    	addSequential(new EndAll(),.2);
    	addSequential(new DriveRaw(.4, 0), .4);
    	
    }
    
    

}
