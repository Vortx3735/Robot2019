package frc.robot.commands.drive.movedistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GetOffHab2 extends CommandGroup {

    public GetOffHab2() {
        addSequential(new DriveExp(.9,0),.6);
    }
}