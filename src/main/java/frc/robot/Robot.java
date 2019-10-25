/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.ArduinoCo;
import frc.robot.subsystems.BallArms;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Navigation;
import frc.robot.subsystems.Shoot;
import frc.robot.util.hardware.VortxPDP;

public class Robot extends TimedRobot {

	// Hardware Subsystems
	public static Drive drive;
	public static BallIntake ballIntake;
	public static HatchIntake hatchIntake;
	public static Elevator elevator;
	public static Carriage carriage;
	public static LimeLight limelight;
	public static BallArms ballArms;
	public static Shoot shoot;
	// Software Subsystems
	public static ArduinoCo arduino;
	public static Autonomous autoLogic;
	public static Navigation navigation;
	public static OI oi;
	public static VortxPDP pdp;

	/************************************
	 * General Robot
	 ******************************************/

	@Override
	public void robotInit() {
		drive = new Drive();
		ballIntake = new BallIntake();
		hatchIntake = new HatchIntake();
		elevator = new Elevator();
		carriage = new Carriage();
		ballArms = new BallArms();
		shoot = new Shoot();

		arduino = new ArduinoCo();
		limelight = new LimeLight();
		navigation = new Navigation();
		pdp = new VortxPDP();

		autoLogic = new Autonomous();

		oi = new OI();
	}

	@Override
	public void robotPeriodic() {
		// only reads and prints the values to smartdashboard
		log();
	}

	/************************************
	 * Disabled Robot
	 ******************************************/

	@Override
	public void disabledInit() {
		// SmartBoard.setPreMatchMode();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**************************************
	 * Auto*********************************************
	 */

	@Override
	public void autonomousInit() {
		elevator.resetEncoderPositions();
		ballArms.startConsPower();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/************************************
	 * Teleop
	 ******************************************/

	@Override
	public void teleopInit() {
		ballArms.startConsPower();
		drive.sickoMode = false;
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/************************************
	 * Test
	 ******************************************/

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
	}

	public void log() {
		// SmartDashboard.putData(Scheduler.getInstance());
		// limelight.log();
		// drive.log();
		// ballIntake.log();
		// hatchIntake.log();
		elevator.log();
		// carriage.log();
		// navigation.log();
		// arduino.log();
		// pdp.log();
		// ballArms.log();
	}

	public void debugLog() {
		// drive.debugLog();
		// ballIntake.debugLog();
		elevator.debugLog();
	}

}
