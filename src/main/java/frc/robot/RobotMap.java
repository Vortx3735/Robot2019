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

		public static int[] leftTrain =  {2,1,0}; //values for Robot2019
		public static int[] rightTrain = {-9,-10,-8};
	}

	public static class Ball {
		public static int topMotor = 5; //5

	}

	public static class Hatch {
		public static int solenoid = 1;
	}
	
	public static class Carriage {
		public static int solenoid = 0;
	}
	public static class Elevator {
		public static int[] elevatorMotors = {-7,6};
	}

	public static class Winch {
		public static int solEndLeft = 3;
		public static int solEndRight = 4;

		public static int winch = 0;
	}

}
