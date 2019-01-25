/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Drive{

		public static int[] leftTrain =  {2,3}; //values for kitbot
		public static int[] rightTrain = {-10,-11};
	}

	public static class Intake {
		public static int topMotor = 8;

	}

	public static class Hatch {
		public static int solenoid = 4;
	}
	
	public static class elevator {
		public static int leftElev= 9;//5;
		public static int rightElev = 1;
	}

	public static class endGame
	{
		public static int solEndLeft = 2;
		public static int solEndRight = 3;

		public static int winch = 7;
	}

}
