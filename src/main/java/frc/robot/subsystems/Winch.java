package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.hardware.VortxTalon;

/**
 * Add your docs here.
 */
public class Winch extends Subsystem {

  VortxTalon motor;

  public Winch() {
//    super("winch","WCH");
    motor = new VortxTalon(RobotMap.Winch.winch, "Winch");
  }

  public void setMotorSpeed(double d) {
    motor.set(ControlMode.PercentOutput, d);
  }

  public void log() {    
    SmartDashboard.putNumber("Winch Pully Speed",motor.get()); 
  }

  @Override
  public void initDefaultCommand() {

  }
  
}