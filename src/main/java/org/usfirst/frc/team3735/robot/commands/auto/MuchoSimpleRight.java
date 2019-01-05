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
public class MuchoSimpleRight extends CommandGroup {

    public MuchoSimpleRight(boolean shoot) {
//    	addSequential(new MoveDDx(-Dms.Field.TOBASELINE - 36, .6, .05).addA(new NavxAssist()));
    	addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), 2.4);
    	addParallel(new PivotReset(), 1.0);
    	
    	if(shoot){
    		addSequential(new TurnTo(85), 1.25);
    		addSequential(new ElevatorSetPosPID(Elevator.switchHeight, false),1);
    		addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), .5);
    		addSequential(new CarriageSetRoller(-.5).addT(new CarriageOverload(900)),2);
    		addSequential(new ElevatorNoCurrent(), 3);
    	}
    }
    
    public void initialize() {
    	System.out.println("starting very simple");
    }
    
    public void end() {
    	System.out.println("ending very simple");
    }
}
