package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.Wait;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageLower;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaise;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotSetPID;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveRaw;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.triggers.LocationProximity;
import org.usfirst.frc.team3735.robot.util.choosers.Side;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleLineup extends CommandGroup {

	double prox = 50;
	
    public AutoScaleLineup(boolean right) {
    	Location target = (right) ? Waypoints.Pieces.scaleRight : Waypoints.Pieces.scaleLeft;
    	
    	
    	//Turn to target, go, and elevator up
    	addSequential(new PivotSetPID(0, true), 1);
    	addSequential(new TurnTo(target, true),1);
    	addSequential(
			VortxCommand.asParallel(
				VortxCommand.asSequence(
					new ElevatorSetPosDDx(Elevator.top, .8, .02),
					new ElevatorSetPosPID(Elevator.top, true)
				)
				,new DriveExp(-.5, 0).addT(new LocationProximity(target, prox)).addA(new NavxAssist(target, true))
    		)
    	,3);	
    	
    	
    	
    	//shoot it
    	addSequential(new CarriageLower());
    	addParallel(new CarriageSetRoller(-.6), 1);
    	addSequential(new Wait(.2));
    	
    	//back up same dist, lower elevator and carriage
    	addSequential(new DriveExp(.5, 0).addT(new HasMoved(30)).addA(new NavxAssist()));
    	
//    	addSequential(new CarriageLower());
    	addSequential(new ElevatorSetPosDDx(0, .7, .03));

    	
    	
    	
    }
    
    

}
