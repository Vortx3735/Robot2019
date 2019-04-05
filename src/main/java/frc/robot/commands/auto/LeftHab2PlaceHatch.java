package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.drive.TurnTo;
import frc.robot.commands.drive.movedistance.DriveExp;
import frc.robot.commands.drive.profiling.DriveToTargetP;
import frc.robot.commands.hatch.HatchSet;


public class LeftHab2PlaceHatch extends CommandGroup {

    public LeftHab2PlaceHatch() {
        addSequential(new HatchSet(true),.5);
        addSequential(new DriveExp(.9, 0),.8);
        addParallel(new CarriageSolenoidSet(true));
        // addSequential(new TurnTo(60),.7);
        // addSequential(new DriveExp(.7,0),1);
        // addSequential(new TurnTo(0),.7);
        // addSequential(new DriveToTargetP(), 2);
        // addSequential(new HatchSet(false),.5);
    }
}