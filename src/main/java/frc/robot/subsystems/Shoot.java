/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Shoot extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Solenoid s1;


  public Shoot()
  {
    s1 = new Solenoid(RobotMap.Shoot.shoot);
  }
  public void isEnabled(boolean toggle)
  {
    s1.set(toggle);

  } 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
