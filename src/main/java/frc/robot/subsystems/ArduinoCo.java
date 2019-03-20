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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ArduinoCo extends Subsystem {

  double distance;
  public static SerialPort sp;
  int count;
  String builder;

  public ArduinoCo() {
    sp = new SerialPort(9600, Port.kUSB);
    builder = "";
  }

  public double getDistance() {
    return distance;
  }

  public void update() {
    // System.out.println("Update was called");
    count++;
    try {
      if (sp.getBytesReceived() > 0) {
        builder += sp.readString();
      }
      if(builder.contains("[0-9]{3}.[0-9]{2}")) {
        
      }
      if(count%8==0) {
        // int first = builder.indexOf("\n");
        // int last = builder.lastIndexOf("\n");
        // if(first!=last && last > first) {
        //   String data = builder.substring(builder.indexOf("\n"), builder.lastIndexOf("\n")).trim();
        //   if(data.length()==5) {
        //     System.out.println(data);
        //   } else {
        //     data = data.substring(0, data.indexOf("\n"));
        //     if(data.length()==5) {
        //       System.out.println(data);
        //     }
        //   }
        System.out.println(builder);
          
          builder = "";
        

      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void log() {
    update();
    SmartDashboard.putNumber("Arduino Distance", distance);
  }

  @Override
  protected void initDefaultCommand() {

  }

}
