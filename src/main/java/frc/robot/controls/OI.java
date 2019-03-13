/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import frc.robot.util.calc.VortxMath;
import frc.robot.util.oi.XboxController;
import frc.robot.util.settings.Func;
import frc.robot.util.smartboard.SmartBoard;
import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.EndAll;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.drive.profiling.PathFollower;
import frc.robot.commands.hatch.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.sequences.PlaceHatch;
import frc.robot.commands.elevator.ElevatorSetPos;
import frc.robot.commands.auto.InchesStraight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XboxController main;
	public XboxController co;
	int counter = 0;

	public OI() {

		///////////////////////MAIN CONTROLS//////////////////////////////

		main = new XboxController(0);
		co = new XboxController(1);

		main.a.whenPressed(new PlaceHatch(new Func() {
			@Override
			public double getValue() {
				return main.lb.get() ? Constants.Elevator.lowRocketCargo : Constants.Elevator.lowRocketHatch;
			}
		}));
		main.b.whenPressed(new PlaceHatch(new Func() {
			@Override
			public double getValue() {
				return main.lb.get() ? Constants.Elevator.midRocketCargo : Constants.Elevator.midRocketHatch;
			}
		}));
		main.y.whenPressed(new PlaceHatch(new Func() {
			@Override
			public double getValue() {
				return main.lb.get() ? Constants.Elevator.highRocketCargo : Constants.Elevator.highRocketHatch;
			}
		}));
		
		main.x.whenPressed(new HatchToggle());

		main.pov0.whenPressed(new CarriageSolenoidSet(true));
		main.pov180.whenPressed(new CarriageSolenoidSet(false));

		//////////////////////////////CO Controls//////////////////////////////

		co.a.whenPressed(new PlaceHatch(new Func() {
			@Override
			public double getValue() {
				return co.lb.get() ? Constants.Elevator.lowRocketCargo : Constants.Elevator.lowRocketHatch;
			}
		}));
		co.b.whenPressed(new PlaceHatch(new Func() {
			@Override
			public double getValue() {
				return co.lb.get() ? Constants.Elevator.midRocketCargo : Constants.Elevator.midRocketHatch;
			}
		}));
		co.y.whenPressed(new PlaceHatch(new Func() {
			@Override
			public double getValue() {
				return co.lb.get() ? Constants.Elevator.highRocketCargo : Constants.Elevator.highRocketHatch;
			}
		}));
		
		co.x.whenPressed(new HatchToggle());

		main.pov0.whenPressed(new CarriageSolenoidSet(true));
		main.pov180.whenPressed(new CarriageSolenoidSet(false));

		main.rb.whenPressed(new InchesStraight(50));

	}

	//
	public double getDriveMove() {
		double move = main.getRightTrigger() - main.getLeftTrigger();
		return Math.copySign((move*move), move);
		//return main.getRightTrigger() - main.getLeftTrigger();
	}

	public double getDriveTurn() {
		double turn = main.getLeftX();
		return  Math.copySign((turn*turn), turn);
	}
	
	public double getElevatorMove() {
		double move = main.getRightY()+ co.getLeftY();
		return  Math.copySign((move*move), move);
	}

	public double getBallMove() {
		return co.getRawAxis(5)*-1;
	}
	
}
