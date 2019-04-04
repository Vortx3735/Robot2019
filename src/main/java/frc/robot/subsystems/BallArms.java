package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ballarms.BallArmsMoveJoystick;
import frc.robot.util.PIDCtrl;
import frc.robot.util.hardware.VortxTalon;

public class BallArms extends Subsystem implements PIDSource, PIDOutput {

  VortxTalon motor;

  public PIDCtrl controller;

  public BallArms() {
//    super("winch","WCH");
    motor = new VortxTalon(RobotMap.BallArms.ballArms, "BallArms");

    motor.initSensor(FeedbackDevice.QuadEncoder, false);
    motor.setSelectedSensorPosition(0);

    controller = new PIDCtrl(.004,.01,0,0,this,this,2);
		SmartDashboard.putData(controller);
		controller.setAbsoluteTolerance(5);
		controller.setOutputRange(-.7, .7);
		controller.disable();
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
  
  @Override
  public void pidWrite(double output) {
    setMotorSpeed(output);//(ControlMode.PercentOutput, output);
  }

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {}

  @Override
  public PIDSourceType getPIDSourceType() {
    return PIDSourceType.kDisplacement;
  }

  @Override
  public double pidGet() {
    return getPosition();
  }

  public void log() {    
    SmartDashboard.putNumber("Ball Arms Speed", motor.get());
    SmartDashboard.putNumber("Ball Arms Position", getPosition());
    SmartDashboard.putNumber("Ball Arms Motor Temp", motor.getTemperature());
  }

}