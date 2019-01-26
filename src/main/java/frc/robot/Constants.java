package frc.robot;

public class Constants {

	public static final double dt = .05; //seconds
	
	public class Drive {

		//TODO: testing on these values
		public static final double maxVelocity = 1.5; //m/s
		public static final double maxAccel = 2; //m/s^2
		public static final double maxJerk = 40; //m/s^3
		public static final double wheelBase = 0.5; //m
		public static final double wheelDiam = 0.1016; //m  //4 in to m
		public static final int ticksPerRotation = 4096; //4096 for mag encoder
		public static final double InchesPerRotation = 4*Math.PI;


		//54.7725 ticks per inch
		public static final double InchesPerTick = InchesPerRotation/ticksPerRotation;
		//.018257337;//.018718924;	//in inches (jamal)

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

	public class Jevois {
		public static final double focalLength = 0.0;
		public static final double fieldOfVision = 0.0;
	}

	public class PathFinder {

	}
}
