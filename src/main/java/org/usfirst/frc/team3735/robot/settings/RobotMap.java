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
