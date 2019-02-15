/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import javax.xml.stream.events.EndElement;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.hardware.VortxTalon;

public class Elevator extends Subsystem {

    VortxTalon l1;
    VortxTalon R1;
    int lval;
    int Rval;
    public Elevator()
    {
      l1 = new VortxTalon(RobotMap.elevator.leftElev);
      R1 = new VortxTalon(RobotMap.elevator.rightElev);
    }

    public void setElevator(double d)
    {
      l1.set(ControlMode.PercentOutput, d);
      R1.set(ControlMode.PercentOutput, d);
    }

    public void log()
    {
      SmartDashboard.putNumber("LeftElevatorSpeed", l1.get());
      SmartDashboard.putNumber("RightElevatorSpeed", R1.get());
    }

  @Override
  public void initDefaultCommand() {
 
  }
}
