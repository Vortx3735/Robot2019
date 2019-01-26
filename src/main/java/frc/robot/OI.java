/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.robot.commands.auto.MeterStraight;
import frc.robot.commands.Winch.SetWinchGame;
import frc.robot.commands.Winch.SetWinchSpeed;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.commands.intake.BallIntakeMotorSet;

import frc.robot.util.oi.XboxController;

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
		main.rb.get();
		main.a.whileHeld(new BallIntakeMotorSet(0.75));
		main.b.whileHeld(new BallIntakeMotorSet(-0.75));
		main.x.whileHeld(new BallIntakeMotorSet(0.0));
		main.lb.whenPressed(new HatchSet(true));
		main.rb.whenPressed(new HatchSet(false));
		//main.pov0.whenPressed(new elevatorSet(0.2));
		main.pov90.whenPressed(new SetWinchGame(true));
		main.pov270.whenPressed(new SetWinchGame(false));

		main.start.whileHeld(new SetWinchSpeed(0.25));
		main.back.whenPressed(new SetWinchSpeed(0.0));
		

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
	
}
