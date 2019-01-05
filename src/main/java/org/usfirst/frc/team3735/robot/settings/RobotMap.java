//values for the practice robot

//package org.usfirst.frc.team3735.robot;
//
//public class RobotMap {
//
//	public static class Drive{
//		public static int leftMotor1 =		4;
//		public static int leftMotor2 = 		5;
//		public static int leftMotor3 = 		6;
//		
//		public static int rightMotor1 = 	1;
//		public static int rightMotor2 = 	2;
//		public static int rightMotor3 = 	3;
//	}
//	public static class GearIntake{
//		public static final boolean topRollerInverted = true;
//		public static int topRoller = 		8;
//		public static int topFeedSolenoid =			1;
//		public static int liftSolenoid = 			0;
//	}
//	public static class Shooter{
//		public static final boolean agitatorInverted = false;
//		public static final boolean drumInverted = false;
//		public static int drum = 			7;
//		public static int drum2 = 			9;
//		public static int agitator =   		13;
//	}
//	public static class BallIntake{
//		public static final boolean rollerInverted = true;
//		public static int roller = 			11;
//	}
//	public static class Scaler{
//		public static final boolean scalerInverted = true;
//		public static int motor = 			10;
//		public static int motor2 = 			12;
//
//
//	}
//		
//}

//values for the final robot

package org.usfirst.frc.team3735.robot.settings;

public class RobotMap {

	public static class Drive{
		
		public static int[] leftTrain = 			{2,3};//2,3
		public static int[] rightTrain = 			{-10,-11};

		
	}
	
	public static class CubeIntake{
		public static int leftMotor = 			 	1;
		public static int rightMotor = 				-12;
		
		public static int anglerMotor = 		 	6;//-6 for final, 6 for practice
		public static boolean reversedSensor = 		false;

		public static int solenoid = 		1;
		public static int solenoid2 = 		3;
		
	}
	
	
	
	public static class Elevator{
		public static int elevatorLeft = 		 	8;//8
		public static int elevatorRight = 			-9;//-9			//-6 for practice, -9 for final
		
		public static boolean reversedSensor = 		true;
		
	}
	
	public static class Carriage{
		public static int carriageLeft = 			-4;
		public static int carraigeRight =		 	 5;
		
		public static int solenoid = 		2;
	}
	
	public static class Climber{
		public static int motor = 					 7;
		
	}
}
