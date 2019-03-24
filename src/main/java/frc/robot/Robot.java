/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.util.choosers.AutoChooser;
import frc.robot.util.choosers.DoNothing;
import frc.robot.util.hardware.VortxPDP;
import edu.wpi.cscore.UsbCamera;
import frc.robot.commands.ZeroAll;
import frc.robot.commands.auto.LeftHab2PlaceHatch;
import frc.robot.commands.auto.MiddleHatch;
import frc.robot.commands.auto.RightHab2PlaceHatch;
import frc.robot.controls.OI;

public class Robot extends TimedRobot {

	// Hardware Subsystems
	public static Drive drive;
	public static BallIntake ballIntake;
	public static HatchIntake hatchIntake;
	public static Elevator elevator;
	public static Winch endgame;
	public static Carriage carriage;
	public static LimeLight limelight;
	public static Suck suck;
	public static SuckArms suckArms;

	// Software Subsystems
	public static ArduinoCo arduino;
	public static Autonomous autoLogic;
	public static Navigation navigation;
	public static OI oi;
	public static VortxPDP pdp;
	
	/** Understanding how the methods are called.
	 *  
	 *  The states are divided into general robot, disabled, and enabled
	 *  Enabled is furhter divided into modes: Teleop, Autonomous,and Test 
	 *  
	 *  Each one of these have their own:
	 * 
	 *  Init method - called once each time
	 *  Periodic method - called periodically while in that mode
	 * 
	 *  NOTE: Practice causes the robot to cycle through the stages as it would in an acutal match.
	 */


	/************************************ General Robot ******************************************/

	@Override
	public void robotInit() {
		drive = new Drive();
		ballIntake = new BallIntake();
		hatchIntake = new HatchIntake();
		elevator = new Elevator();
		carriage = new Carriage();
		endgame = new Winch();
		suck = new Suck();
		suckArms = new SuckArms();

		arduino = new ArduinoCo();
		limelight = new LimeLight();
		navigation = new Navigation();
		pdp = new VortxPDP();
		
		autoLogic = new Autonomous();

		oi = new OI();

	}

	@Override 
	public void robotPeriodic() {
		//only reads and prints the values to smartdashboard
		log();
	}

	/************************************ Disabled Robot ******************************************/

	@Override
	public void disabledInit() {
//		SmartBoard.setPreMatchMode();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**************************************Auto********************************************* */

	@Override
	public void autonomousInit() {
		autoLogic.startCommand();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		navigation.integrate();
		
	}

	/************************************ Teleop ******************************************/

	@Override
	public void teleopInit() {
//		SmartBoard.setMatchMode();
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		navigation.integrate();
		log();
	}

	/************************************ Test ******************************************/

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}

	public void log() {
		SmartDashboard.putData(Scheduler.getInstance());
		drive.log();
		//ballIntake.log();
		hatchIntake.log();
		elevator.log();
		carriage.log();
		navigation.log();
		arduino.log();
		pdp.log();
	} 

	public void debugLog() {
		drive.debugLog();
		//ballIntake.debugLog();
		elevator.debugLog();
	}

}
