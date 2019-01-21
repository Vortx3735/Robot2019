/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.robot.commands.Elevator.elevatorSet;

import frc.robot.commands.endGame.setEndGame;
import frc.robot.commands.hatch.HatchSet;
import frc.robot.commands.intake.ballIntakeMotorSet;

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
		main.a.whileHeld(new ballIntakeMotorSet(0.5));
		main.b.whileHeld(new ballIntakeMotorSet(-0.5));
		main.x.whileHeld(new ballIntakeMotorSet(0.0));
		main.lb.whenPressed(new HatchSet(true));
		main.rb.whenPressed(new HatchSet(false));
		//main.pov0.whenPressed(new elevatorSet(0.2));
		main.pov90.whenPressed(new setEndGame(true));
		main.pov270.whenPressed(new setEndGame(false));
		
		

		
	
		
		

	}
	//
	public double getDriveMove() {
		return (main.getRightTrigger() - main.getLeftTrigger());
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		return  main.getLeftX();
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
