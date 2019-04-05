package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BackToggle extends Command {

  @Override
  protected void initialize() {
    Robot.shoot.setBackSolenoid(!Robot.shoot.backSolenoid.get());
  }

  @Override
  protected boolean isFinished() {
    return true;
    
  }

}
