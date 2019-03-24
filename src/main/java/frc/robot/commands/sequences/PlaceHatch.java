package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.Robot;

public class PlaceHatch extends CommandGroup {

    public PlaceHatch() {

        requires(Robot.carriage);
        requires(Robot.hatchIntake);

        addSequential(new HatchSet(false), .5);
    }

}