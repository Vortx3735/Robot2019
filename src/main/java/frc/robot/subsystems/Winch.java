package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.Winch.SetWinchSpeed;
import frc.robot.util.hardware.VortxTalon;

/**
 * Add your docs here.
 */
public class Winch extends Subsystem {
  Solenoid solLeft;
  Solenoid solRight;
  VortxTalon motor;

  public Winch() {
    solLeft = new Solenoid(RobotMap.Winch.solEndLeft);
    solRight = new Solenoid(RobotMap.Winch.solEndRight);
    motor = new VortxTalon(RobotMap.Winch.winch);
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
  }
}