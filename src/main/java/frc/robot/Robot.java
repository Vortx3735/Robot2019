/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import frc.robot.util.smartboard.SmartBoard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
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

	// Software Subsystems
	public static ArduinoCo arduino;
	public static Autonomous autoLogic;
	public static Navigation navigation;
	public static OI oi;
	public static UsbCamera camera1;
	
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
		//endgame = new Winch();
		//arduino = new ArduinoCo();
		//limelight = new LimeLight();
		//navigation = new Navigation();

		System.out.println("Robot init was called");
		oi = new OI();
		
		autoLogic = new Autonomous();

		SmartBoard.start();

//TODO add camera

		//camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		//camera1.setResolution(320, 240);
		//camera1.setFPS(15);

		//NetworkTableInstance.getDefault().getEntry("/CameraPublisher/MyCamera/streams").setStringArray(new String[] {"mjpg:http://10.37.35.11:5800/?action=stream"});
	}

	@Override 
	public void robotPeriodic() {
		//only reads and prints the values to smartdashboard
		//drive.log();
		//ballIntake.log();
		//hatchIntake.log();
		//endgame.log();
		//navigation.log();
	}

	/************************************ Disabled Robot ******************************************/

	@Override
	public void disabledInit() {
		SmartBoard.setPreMatchMode();
	}

	@Override
	public void disabledPeriodic() {
	}

	/************************************ Teleop ******************************************/

	@Override
	public void teleopInit() {
		SmartBoard.setMatchMode();
	}

	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		drive.debugLog();
	}

	/************************************ Test ******************************************/

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}

}
