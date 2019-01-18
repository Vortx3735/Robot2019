package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.assists.NavxAssist;
import frc.robot.commands.drive.movedistance.DriveExp;

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
