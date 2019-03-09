package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.ElevatorSetPos;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.commands.carriage.CarriageSolenoidSet;

public class PlaceHatch extends CommandGroup {

    public PlaceHatch(double height) {
        addSequential(new ElevatorSetPos(height,true));
        addSequential(new CarriageSolenoidSet(true), .25);
        addSequential(new HatchSet(false), .25);
        addSequential(new CarriageSolenoidSet(false), .25);
        addSequential(new ElevatorSetPos(0, true));
    }
}