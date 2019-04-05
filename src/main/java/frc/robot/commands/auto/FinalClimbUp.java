/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.movedistance.DriveExp;
import frc.robot.commands.shoot.BackToggle;
import frc.robot.commands.shoot.FrontToggle;


public class FinalClimbUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public FinalClimbUp() {
    addSequential(new BackToggle(), 1.5);       //pushing back up
    addSequential(new DriveExp(-.7, 0), .8); //drive up
    addSequential(new BackToggle(), 1.5);      //get front wheels on
    addSequential(new DriveExp(-.6, 0), .3);  //get some foward
    addSequential(new FrontToggle(), 1.5);      //push the front up
    // addSequential(new DriveExp(-.65,0),1.10);    //drive foward more
    // addSequential(new FrontToggle(), 3);        //lift up front wheels
    // addSequential(new DriveExp(-.5,0), .7);   //pull foward more

  }
}
