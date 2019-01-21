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
public class endGame extends Subsystem {
  Solenoid solLeft;
  Solenoid solRight;

  public endGame()
  {
    solLeft = new Solenoid(RobotMap.endGame.solEndLeft);
    solRight = new Solenoid(RobotMap.endGame.solEndRight);
  }

  public void setEnd(boolean bol)
  {
    solLeft.set(bol);
    solRight.set(bol);
  }
  @Override
  public void initDefaultCommand() {
  
  }
}
