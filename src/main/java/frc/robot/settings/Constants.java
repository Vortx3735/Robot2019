package org.usfirst.frc.team3735.robot.settings;

public class Constants {
	
	public class Drive{
		
//		public static final double InchesPerTick = .00309196;	//in inches (bempest)
		
		//54.7725 ticks per inch
		public static final double InchesPerTick = .018257337;//.018718924;	//in inches (jamal)

		//exp drive
		public static final double moveReactivity = 1;	//(0,1] (least reactive, most reactive]
		public static final double turnReactivity = 1;	//(0,1] (least reactive, most reactive]	
		public static final double scaledMaxMove = 1;
		public static final double scaledMaxTurn = .7;	//(0,1] directly to the arcadedrive turn value
		//these retain the range but shift more of the action towards lower values as the exponent is raised higher
		//graph y = x * x^(p-1) {-1 < x < 1} for visualization
		public static final double moveExponent = 3;		//[1,inf) 1 is linear, 2 is squared (normal), etc.
		public static final double turnExponent = 3;		//[1,inf) 

		//for turning slowly with lb and rb
		public static final double lowSensitivityLeftTurn = -.2;
		public static final double lowSensitivityRightTurn = .2;
		
		public static final boolean isUsingLeftEncoders = true;
		public static final boolean isUsingRightEncoders = true;

		public static final double driveTolerance = 2;

	}
	
	public class CubeIntake{
		public static final double cubeIntakeSpeed = .5;
	}
	
	public class Elevator{
		public static final double elevatorSpeed = 0.5;
		
		public static final double elevatorMultiplier = 0.2;//.5
		public static final double correctionMultiplier = 0.3;//.12
		
		public static final double ticksPerInch =835.22; 
		
		public static final double dPLeft = 80;
		public static final double dILeft = .15;
		public static final double dDLeft = 60;
		public static final double dFLeft = 0.0;
		public static final int iZoneLeft = 2;
		
		public static final double dPRight = 80;
		public static final double dIRight = .15;
		public static final double dDRight = 60;
		public static final double dFRight = 0.0;
		public static final int iZoneRight = 2;
	}
	
	public class Carriage{
		public static final double carriageSpeed = 1.0;
	}
	
	public class Climber{
		public static final double initialSpeed = 0.5;
		public static final double tensionSpeed = 1.0;		
	}

}