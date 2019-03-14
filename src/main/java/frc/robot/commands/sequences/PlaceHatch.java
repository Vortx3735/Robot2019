package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.Robot;
import frc.robot.commands.carriage.CarriageSolenoidSet;

public class PlaceHatch extends CommandGroup {

    public PlaceHatch() {

        requires(Robot.carriage);
        requires(Robot.hatchIntake);

        addSequential(new CarriageSolenoidSet(true), .5);
        addSequential(new HatchSet(false), .5);
        addSequential(new CarriageSolenoidSet(false), .5);
    }

}