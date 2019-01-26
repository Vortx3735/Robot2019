/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ArduinoCo extends Subsystem {

  int distance;
  Thread nThread;
  public static SerialPort sp;

  public ArduinoCo() {
    sp = new SerialPort(9600, Port.kUSB);
  }

  public int getDistance() {
    return distance;
  }

  public void update() {
    // System.out.println("Update was called");
    try {
      if (sp.getBytesReceived() > 0) {
        String[] input = sp.readString().trim().split("\n");

        if (!input.equals("")) {
          distance = Integer.parseInt(input[input.length - 1]);
        }
      }
    } catch (NumberFormatException e) {
    }

  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
