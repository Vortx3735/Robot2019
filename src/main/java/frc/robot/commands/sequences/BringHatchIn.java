package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.elevator.ElevatorSetPos;
import frc.robot.commands.hatch.HatchToggle;
import frc.robot.util.settings.Func;

public class BringHatchIn extends CommandGroup {

    public BringHatchIn () {
        addSequential(new ElevatorSetPos(new Func() {
			@Override
			public double getValue() {
				return 10;
			}
        }, false), 1);
        
        addSequential(new CarriageSolenoidSet(false), .5);
        addSequential(new ElevatorSetPos(new Func() {
			@Override
			public double getValue() {
				return 0;
			}
		}, false));
    }
    
}