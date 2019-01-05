package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.Wait;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageLower;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotSetPID;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveRaw;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
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
public class CubeTransfer extends CommandGroup {

    public CubeTransfer() {
    	requires(Robot.elevator);
    	
    	
    	addParallel(new CubeSetRoller(-.3),2);
    	addParallel(new ElevatorSetPosPID(Elevator.transferHeight, false),2);
    	addSequential(new Wait(.2));
    	addSequential(new PivotSetPID(130, false),1);
    	
    	addParallel(new CubeSetRoller(.5),1);
    	addSequential(new CarriageSetRoller(-.35).addT(new CarriageOverload(900)),.45);
    	addSequential(new PivotSetPID(0, false), .6);
    	
    	
//    	addParallel(new CubeSetRoller(-.3),2);
//    	addSequential(new ElevatorSetPosPID(Elevator.transferHeight, true),1);
//    	addSequential(new PivotSetPID(130, true),1);
//    	addParallel(new ElevatorSetPosPID(Elevator.transferHeight, false),1);
//    	addParallel(new PivotSetPID(130, false),1);
//    	
//    	
//    	addParallel(new CubeSetRoller(.5),1);
//    	addSequential(new CarriageSetRoller(-.35).addT(new CarriageOverload(900)),.45);
//    	addSequential(new PivotSetPID(70, false),1);
    	

    	
    }
    
    

}
