/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.movedistance.DriveExp;
import frc.robot.commands.intake.BallIntakeMotorSet;
import frc.robot.commands.shoot.ShootToggle;
import frc.robot.commands.suckarms.ArmsDown;

public class FinalClimbUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public FinalClimbUp() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    addSequential(new ArmsDown(),3);
    //addSequential(new BallIntakeMotorSet(0.5), 1);
    // addSequential(new ShootToggle(true));
     addSequential(new DriveExp(-.3,0),3);
    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
