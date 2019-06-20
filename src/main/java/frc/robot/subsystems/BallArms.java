package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.ballarms.BallArmsConsPower;
import frc.robot.commands.ballarms.BallArmsMoveJoystick;
import frc.robot.util.PIDCtrl;
import frc.robot.util.hardware.VortxTalon;

public class BallArms extends Subsystem{

  VortxTalon motor;

  Command consPower;


  public BallArms() {
//    super("winch","WCH");
    motor = new VortxTalon(RobotMap.BallArms.ballArms, "BallArms");

    motor.initSensor(FeedbackDevice.QuadEncoder, true); //TODO: direction and start
    motor.setSelectedSensorPosition(0);
  

  }

  public void startConsPower() {
    consPower = new BallArmsConsPower(Constants.BallArms.CONSPOWERSTART);
    consPower.start();

  }

  public void setMotorSpeed(double d) {
    motor.set(ControlMode.PercentOutput, d);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new BallArmsMoveJoystick());
  }

  public double getPosition() {
    return motor.getSelectedSensorPosition();
  }

  public void log() {    
    SmartDashboard.putNumber("Ball Arms Speed", motor.get());
    SmartDashboard.putNumber("Ball Arms Position", getPosition());
    SmartDashboard.putNumber("Ball Arms Motor Temp", motor.getTemperature());
  }

}