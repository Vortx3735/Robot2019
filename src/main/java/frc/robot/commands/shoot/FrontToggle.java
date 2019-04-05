package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FrontToggle extends Command {

  @Override
  protected void initialize() {
    Robot.shoot.setFrontSolenoid(!Robot.shoot.frontSolenoid.get());
  }

  @Override
  protected boolean isFinished() {
    return true;
    
  }

}
