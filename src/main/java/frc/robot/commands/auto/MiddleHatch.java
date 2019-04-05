package frc.robot.commands.auto;


import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.profiling.DriveToTargetP;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.drive.movedistance.DriveExp;

public class MiddleHatch extends CommandGroup{


    public MiddleHatch() {
        addSequential(new HatchSet(true),.5);
        addSequential(new CarriageSolenoidSet(true),.2);
        addSequential(new DriveExp(.8,0), .8);
        //addSequential(new DriveToTargetP(), 2);
    }
}