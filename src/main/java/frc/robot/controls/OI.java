/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import frc.robot.util.calc.VortxMath;
import frc.robot.util.oi.XboxController;
import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.carriage.CarriageSolenoidSet;
import frc.robot.commands.drive.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.winch.*;

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

		//main.applyControls(new MainDriverControllerMap());
		main.a.whileHeld(new BallIntakeMotorSet(-0.75)); //in
		main.b.whileHeld(new BallIntakeMotorSet(1.0)); //out

		//Hatch commands
		main.lb.whenPressed(new HatchSet(true));
	    main.rb.whenPressed(new HatchSet(false));


		main.pov0.whenPressed(new CarriageSolenoidSet(true));
		main.pov180.whenPressed(new CarriageSolenoidSet(false));
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
