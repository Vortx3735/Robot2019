/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.*;
import frc.robot.util.smartboard.SmartBoard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.controls.OI;

public class Robot extends TimedRobot {

	// Hardware Subsystems
	public static Drive drive;
	public static BallIntake ballintake;
	public static HatchIntake hatchintake;
	public static Elevator elevator;
	public static Winch endgame;

	// Software Subsystems
	public static ArduinoCo arduinoCo;
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
		ballintake = new BallIntake();
		hatchintake = new HatchIntake();
		elevator = new Elevator();
		endgame = new Winch();
		//arduinoCo = new ArduinoCo();
		navigation = new Navigation();
		//jevois = Jevois();
		oi = new OI();
		
		autoLogic = new Autonomous();

		SmartBoard.start();

		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(320, 240);
		camera1.setFPS(15);

		NetworkTableInstance.getDefault().getEntry("/CameraPublisher/MyCamera/streams").setStringArray(new String[] {"mjpg:http://10.37.35.11:5800/?action=stream"});
	}

	@Override 
	public void robotPeriodic() {
		//only reads and prints the values to smartdashboard
		drive.log();
		ballintake.log();
		hatchintake.log();
		endgame.log();
		navigation.log();
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
	public void teleopPeriodic() {}

	/************************************ Test ******************************************/

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}

}
