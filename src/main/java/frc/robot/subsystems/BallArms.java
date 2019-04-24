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

public class BallArms extends Subsystem implements PIDSource, PIDOutput {

  VortxTalon motor;

  Command consPower;

  public PIDCtrl controllerDown;
  public PIDCtrl controllerUp;

  public BallArms() {
//    super("winch","WCH");
    motor = new VortxTalon(RobotMap.BallArms.ballArms, "BallArms");

    motor.initSensor(FeedbackDevice.QuadEncoder, true); //TODO: direction and start
    motor.setSelectedSensorPosition(0);
    //TODO Redo PID
    controllerDown = new PIDCtrl(.0008,.000,0.0001,0, this,this,5);
    controllerDown.sendToDash("BallArmsDown");
		controllerDown.setAbsoluteTolerance(10);
    controllerDown.setOutputRange(-.4, .4); //TODO: Outputs
    controllerDown.disable();


    controllerUp = new PIDCtrl(.002,.001,0,0, this,this,5);
    controllerUp.sendToDash("BallArmsUp");
		controllerUp.setAbsoluteTolerance(10);
    controllerUp.setOutputRange(-.8, .2); //TODO: Outputs
    controllerUp.disable();


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

  public void setEncoderPos(int pos) {
    motor.setSelectedSensorPosition(pos);
  }
  
  @Override
  public void pidWrite(double output) {
    SmartDashboard.putNumber("PID P Output", output*-1);
    setMotorSpeed(output*-1);

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