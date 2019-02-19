/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.hatch.HatchSet;
import frc.robot.commands.intake.BallIntakeMotorSet;
import frc.robot.commands.winch.SetWinchSolenoids;
import frc.robot.commands.winch.SetWinchSpeed;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.oi.VorTXControllerMap;
import frc.robot.util.oi.XboxController;
import frc.robot.controls.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XboxController main;
	public XboxController co;
	int counter = 0;

	public OI() {

		main = new XboxController(0);
		co = new XboxController(1);

		main.applyControls(new MainDriverControllerMap());
	}

	//
	public double getDriveMove() {
		double move = main.getRightTrigger() - main.getLeftTrigger();
		return Math.copySign((move*move), move);
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		double turn = main.getLeftX();
		return  Math.copySign((turn*turn), turn);
		//return main.getRightX();
	}
	
	public double getFODMag() {
		//return main.getRightMagnitude();
		return 0;
	}
	
	public double getFODAngle(){
		//return main.getRightAngle();
		return 0;
	}

	public double getElevatorMove() {
		return VortxMath.handleDeadband(main.getRightY()+ co.getLeftY(), .05);
	}
	
}
