package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Suck extends Subsystem {

  public Solenoid solenoid;

  public Suck() {
//    super("winch","WCH");
    solenoid = new Solenoid(RobotMap.Winch.solenoid);
  }



  public void setSolenoid(boolean b) {
    solenoid.set(b);
  }

  @Override
  public void initDefaultCommand() {

  }
  
}