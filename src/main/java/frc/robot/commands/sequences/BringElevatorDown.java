/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.ElevatorResetEncoder;
import frc.robot.commands.elevator.ElevatorSetPos;
import frc.robot.util.settings.Func;

public class BringElevatorDown extends CommandGroup {
  /**
   * Add your docs here.
   */
  public BringElevatorDown() {
		addSequential(new ElevatorSetPos(new Func() {
			@Override
			public double getValue() {
				return 0;
			}
		}, false), 2.5);
    addSequential(new ElevatorResetEncoder());
  }

}
