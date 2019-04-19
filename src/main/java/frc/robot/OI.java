/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.util.calc.VortxMath;
import frc.robot.util.oi.XboxController;
import frc.robot.util.settings.Func;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.ZeroAll;
import frc.robot.commands.auto.FinalClimbUp;
import frc.robot.commands.ballarms.BallArmsSetPos;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.carriage.CarriageToggle;
import frc.robot.commands.hatch.*;
import frc.robot.commands.shoot.BackToggle;
import frc.robot.commands.shoot.FrontToggle;
import frc.robot.commands.drive.ReverseDrive;
import frc.robot.commands.drive.profiling.DriveToTargetP;
import frc.robot.commands.drive.simple.DriveAddSensitiveLeft;
import frc.robot.commands.drive.simple.DriveAddSensitiveRight;
import frc.robot.commands.elevator.ElevatorFree;
import frc.robot.commands.elevator.ElevatorSetPos;



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
	
		main.b.whenPressed(new BackToggle());
		main.y.whenPressed(new FrontToggle());

		main.back.whenPressed(new ReverseDrive());

		main.start.whenPressed(new FinalClimbUp());

		////////////////////////CO CONTROLS////////////////////////////

		co.x.whenPressed(new HatchToggle());

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

		co.pov270.whenPressed(new CarriageToggle());

		co.pov0.whenPressed(new BallArmsSetPos(Constants.BallArms.STARTING, 0.2, true));  //up 451		-92 //.1-.15
		co.pov90.whenPressed(new BallArmsSetPos(Constants.BallArms.SCORING, 0.0, true)); //rocket low height right	138
		co.pov180.whenPressed(new BallArmsSetPos(Constants.BallArms.INTAKING, 0.2, false)); //adown					284

		co.start.whenPressed(new ElevatorFree());

		SmartDashboard.putData(new ZeroAll());
		//SmartDashboard.putData(new EndAll());
	}

	//
	public double getDriveMove() {
		double move = main.getRightTrigger() - main.getLeftTrigger();
		return Math.copySign((move*move), move);
	}

	public double getDriveTurn() {
		double turn = main.getLeftX();
		return  Math.copySign((turn*turn), turn);
	}
	
	public double getElevatorMove() {
		double move = VortxMath.handleDeadband(co.getLeftY(),.1);
		return  Math.copySign((move*move), move) * .7;
	}

	public double getBallMove() {
		return VortxMath.handleDeadband(co.getRawAxis(5),.1) * -1;
	}

	public double getArmsMove() {
		double move = VortxMath.handleDeadband(co.getRightTrigger() - co.getLeftTrigger(), .1);
		return Math.copySign((move*move), move);
	}
	
}
