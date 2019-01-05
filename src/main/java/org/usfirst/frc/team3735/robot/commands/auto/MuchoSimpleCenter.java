package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotReset;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotSetPID;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorNoCurrent;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.CarriageOverload;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MuchoSimpleCenter extends CommandGroup {

    public MuchoSimpleCenter(boolean right) {
//    	addSequential(new MoveDDx(-Dms.Field.TOBASELINE - 36, .6, .05).addA(new NavxAssist()));
    	
    	
    	if(right){
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), .6);
    		addSequential(new TurnTo(-135), 1.25);
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), 1.6);//1.8
    		addSequential(new TurnTo(180), 1.25);
    		addParallel(new PivotReset(), 1.0);
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), .75);
    		addSequential(new ElevatorSetPosPID(Elevator.switchHeight, false),1);
    		addSequential(new CarriageSetRoller(-.5).addT(new CarriageOverload(900)),2);
    		addSequential(new ElevatorNoCurrent(), 2);
    	}else{
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), .6);
    		addSequential(new TurnTo(135), 1.25);
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), 2.0);//1.8
    		addSequential(new TurnTo(180), 1.25);
    		addParallel(new PivotReset(), 1.0);
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), .75);
    		addSequential(new ElevatorSetPosPID(Elevator.switchHeight, false),1);
    		addSequential(new CarriageSetRoller(-.5).addT(new CarriageOverload(900)),2);	
    		addSequential(new ElevatorNoCurrent(), 2);
    	}
    }
    
    public void initialize() {
    	System.out.println("starting very simple");
    }
    
    public void end() {
    	System.out.println("ending very simple");
    }
}
