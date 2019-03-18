package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.hatch.HatchToggle;

public class DropHatch extends CommandGroup {

        public DropHatch () {
                addSequential(new CarriageSolenoidSet(true),.19);
                addSequential(new HatchToggle());
        }
    
}