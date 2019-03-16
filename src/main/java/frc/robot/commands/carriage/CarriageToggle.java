package frc.robot.commands.carriage;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CarriageToggle extends Command {

    @Override
    protected void initialize() {
       Robot.carriage.setSolenoid(!Robot.hatchIntake.solenoid.get());
    }

    @Override
    protected boolean isFinished() {
        return true;
    } 

}