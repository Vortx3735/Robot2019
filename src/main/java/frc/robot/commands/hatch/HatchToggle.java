package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchToggle extends Command {

    @Override
    protected void initialize() {
       Robot.hatchIntake.setSolenoid(!Robot.hatchIntake.solenoid.get());
    }

    @Override
    protected boolean isFinished() {
        return true;
    } 

}