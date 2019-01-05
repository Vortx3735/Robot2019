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

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MuchoSimpleScaleRight extends CommandGroup {

    public MuchoSimpleScaleRight(boolean shoot) {
//    	addSequential(new MoveDDx(-Dms.Field.TOBASELINE - 36, .6, .05).addA(new NavxAssist()));
    	
    	double time = 4.2;
    	if(RobotController.getBatteryVoltage() > 12.75){
    		time = 4.1;
    	}else if(RobotController.getBatteryVoltage() > 12.65){
    		time = 4.15;
    	}else if(RobotController.getBatteryVoltage() > 12.55){
    		time = 4.225;
    	}else if(RobotController.getBatteryVoltage() > 12.50){
    		time = 4.25;
    	}else if(RobotController.getBatteryVoltage() > 12.425){
    		time = 4.335;
    	}else if(RobotController.getBatteryVoltage() > 12.35){
    		time = 4.36;
    	}else if(RobotController.getBatteryVoltage() > 12.30){
    		time = 4.375;
    	}else if(RobotController.getBatteryVoltage() < 12.30){
    		time = 4.4;
    	} 
//    	System.out.println(RobotController.getBatteryVoltage() + " Time: " + time);
//    	System.out.println(RobotController.getBatteryVoltage() + " Time: " + time);
//    	System.out.println(RobotController.getBatteryVoltage() + " Time: " + time);
    	addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), time);
    	addParallel(new PivotReset(), 1.0);
    	
    	if(shoot){
    		addSequential(new TurnTo(90), 1.25);
    	    addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), .25);
    		addSequential(new ElevatorSetPosPID(Elevator.top, false),2.15);
    		addSequential(new CarriageSetRoller(-.5).addT(new CarriageOverload(900)),2);
    		addSequential(new ElevatorNoCurrent(),3.5);
    	}
    }
    
    public void initialize() {
    	System.out.println("starting very simple");
    }
    
    public void end() {
    	System.out.println("ending very simple");
    }
}
