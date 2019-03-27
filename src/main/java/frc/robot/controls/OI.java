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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.EndAll;
import frc.robot.commands.ZeroAll;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.hatch.*;
import frc.robot.commands.drive.profiling.DriveToTargetP;
import frc.robot.commands.drive.simple.DriveAddSensitiveLeft;
import frc.robot.commands.drive.simple.DriveAddSensitiveRight;
import frc.robot.commands.elevator.ElevatorFree;
import frc.robot.commands.elevator.ElevatorSetPos;
import frc.robot.commands.winch.ConstantPushing;
import frc.robot.commands.winch.StopPushing;


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
		
		main.x.whenPressed(new HatchToggle());

		main.pov0.whenPressed(new CarriageSolenoidSet(true));
		main.pov180.whenPressed(new CarriageSolenoidSet(false));

		main.pov270.whileHeld(new DriveAddSensitiveLeft());
		main.pov90.whileHeld(new DriveAddSensitiveRight());

		main.a.whileHeld(new DriveToTargetP());

		main.start.whenPressed(new ConstantPushing());
		main.back.whenPressed(new StopPushing());

		////////////////////////CO CONTROLS////////////////////////////

		co.x.whenPressed(new HatchToggle());

		co.pov0.whenPressed(new CarriageSolenoidSet(true));
		co.pov180.whenPressed(new CarriageSolenoidSet(false));

		co.start.whenPressed(new ConstantPushing());
		co.back.whenPressed(new StopPushing());

		co.a.whenPressed(new ElevatorSetPos(new Func() {
			@Override
			public double getValue() {
				return co.lb.get() ? Constants.Elevator.lowRocketCargo : Constants.Elevator.lowRocketHatch;
			}
		}));
		co.b.whenPressed(new ElevatorSetPos(new Func() {
			@Override
			public double getValue() {
				return co.lb.get() ? Constants.Elevator.midRocketCargo : Constants.Elevator.midRocketHatch;
			}
		}));
		co.y.whenPressed(new ElevatorSetPos(new Func() {
			@Override
			public double getValue() {
				return co.lb.get() ? Constants.Elevator.highRocketCargo : Constants.Elevator.highRocketHatch;
			}
		}));

		co.rb.whenPressed(new ElevatorFree());

		SmartDashboard.putData(new ZeroAll());
		SmartDashboard.putData(new EndAll());

		
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
		double move = VortxMath.handleDeadband(main.getRightY()+ co.getLeftY(),.05);
		return  Math.copySign((move*move), move);
	}

	public double getBallMove() {
		return VortxMath.handleDeadband(co.getRawAxis(5),.1);
	}

	public double getWinchMove() {
		double move = VortxMath.handleDeadband(co.getRightTrigger() - co.getLeftTrigger(), .05);
		return Math.copySign((move*move), move);
	}

	public double getArmsMove() {
		double move = VortxMath.handleDeadband(co.getRawAxis(4),.1);
		return Math.copySign((move*move), move);
	}
	
}
