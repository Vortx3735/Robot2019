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
public class Shoot extends Subsystem { //TODO: Name this lift up
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Solenoid frontSolenoid;
  public Solenoid backSolenoid;


  public Shoot() {
    frontSolenoid = new Solenoid(RobotMap.Shoot.frontSolenoid);
    backSolenoid = new Solenoid(RobotMap.Shoot.backSolenoid);
  }


  public void setFrontSolenoid(boolean  b) {
    frontSolenoid.set(b);
  } 

  public void setBackSolenoid(boolean b) {
    backSolenoid.set(b);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
}
