package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.suckarms.SuckArmsMoveJoystick;
import frc.robot.util.hardware.VortxTalon;

public class SuckArms extends Subsystem {

  VortxTalon motor;

  public SuckArms() {
//    super("winch","WCH");
    motor = new VortxTalon(RobotMap.SuckArms.suckArms, "SuckArms");
  }

  public void setMotorSpeed(double d) {
    motor.set(ControlMode.PercentOutput, d);
  }

  public void log() {    
    SmartDashboard.putNumber("Suck Arms Speed", motor.get()); 
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SuckArmsMoveJoystick());
  }
  
}