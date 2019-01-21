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
import frc.robot.RobotMap;

public class Elevator extends Subsystem {

    TalonSRX l1;
    TalonSRX R1;
    int lval;
    int Rval;
    public Elevator()
    {
      l1 = new TalonSRX(RobotMap.elevator.leftElev);
      R1 = new TalonSRX(RobotMap.elevator.rightElev);
    }

    public void setElevator(double d)
    {
      l1.set(ControlMode.PercentOutput, d);
      R1.set(ControlMode.PercentOutput, d);
    }
  @Override
  public void initDefaultCommand() {
 
  }
}
