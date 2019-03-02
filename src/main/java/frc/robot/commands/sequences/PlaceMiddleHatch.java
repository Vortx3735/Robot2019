package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.elevator.ElevatorSetPos;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.commands.carriage.CarriageSolenoidSet;

public class PlaceMiddleHatch extends CommandGroup {

    public PlaceMiddleHatch() {
        addSequential(new ElevatorSetPos(Constants.Elevator.midRocketHatch, true));
        addSequential(new CarriageSolenoidSet(true), .25);
        addSequential(new HatchSet(false), .25);
        addSequential(new CarriageSolenoidSet(false), .25);
        addSequential(new ElevatorSetPos(0, true));


    }
}