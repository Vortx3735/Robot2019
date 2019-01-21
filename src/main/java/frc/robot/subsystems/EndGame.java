/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.endGame.SetWinchSpeed;
import frc.robot.util.hardware.VortxTalon;

/**
 * Add your docs here.
 */
public class EndGame extends Subsystem {
  Solenoid solLeft;
  Solenoid solRight;
  VortxTalon motor;

  public EndGame() {
    solLeft = new Solenoid(RobotMap.endGame.solEndLeft);
    solRight = new Solenoid(RobotMap.endGame.solEndRight);
    motor = new VortxTalon(RobotMap.endGame.winch);
  }

  public void setEnd(boolean bol)
  {
    solLeft.set(bol);
    solRight.set(bol);
  }

  public void setMotorSpeed(double d) {
    motor.set(ControlMode.PercentOutput, d);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SetWinchSpeed(Robot.oi.getWinchSpeed()));
  }
}
