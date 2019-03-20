/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArduinoCo extends Subsystem {

  double distance;
  public static SerialPort sp;
  Pattern p;
  Matcher m;
  String s;

  public ArduinoCo() {
    sp = new SerialPort(9600, Port.kUSB);
    s = "";
    p = Pattern.compile("[0-9]{3}\\.[0-9]{2}");
  }

  public double getDistance() {
    return distance;
  }

  public void update() {

    try {
      if (sp.getBytesReceived() > 0) {
        s += sp.readString().trim(); //add the characters that were read in
        m = p.matcher(s);
        if(s.length()>=6&&m.find()) { //if the current string has length greater than 6 and contains the pattern
          for (int i = s.length()-1; i >=0; i-=6) { //loop backwards from end to beginning to find last occurrence
                if(m.find(Math.max(i, 0))) {        //if it finds it from index i or after
                    distance = Double.parseDouble(s.substring(m.start(), m.end()))); //set the distance
                    s = s.substring(m.end());       //remove the string from the end of the last data and before
                    break;                          //break out of the loop
                }
            }
        }    
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
