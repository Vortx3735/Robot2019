package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.settings.Dms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnknownStraight extends CommandGroup {

    public UnknownStraight() {
//    	addSequential(new MoveDDx(-Dms.Field.TOBASELINE - 36, .6, .05).addA(new NavxAssist()));
    	addSequential(new DriveExp(-.7,0).addA(new NavxAssist()), 2.5);
    }
    
    public void initialize() {
    	System.out.println("starting autow");
    }
    
    public void end() {
    	System.out.println("ending autow");
    }
}
