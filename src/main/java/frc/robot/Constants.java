package frc.robot;

public class Constants {

	public static final double dt = .05; //seconds
	
	public class Drive {

		//TODO: testing on these values
		public static final double maxVelocity = 180; //in/s  //i am sure this is very high
		public static final double maxAccel = 68;//in/s^2
		public static final double maxJerk = 500; //in/s^3

		public static final double wheelBase = 29; //in
		public static final double wheelDiam = 4; //in
		//if this is on a different gear then we should spin the wheel ten times and find the displacement
		public static final int ticksPerRotation = 1024; //4096 for mag encoder /1024 for grayhills we are using
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

	public class Elevator {
		//TODO set these values
		public static final double ticksPerInch = 0.0;
		public static final double tickPerRoatation = 512;
		//All these are heights and should assume the 0 is the bottom of the elevator
		public static final double lowRocketHatch = 0;
		public static final double midRocketHatch = 28;
		public static final double highRocketHatch = 56;
		public static final double lowRocketCargo = 8.5;
		public static final double midRocketCargo = 36.5;
		public static final double highRocketCargo = 64.5;
		public static final double cargoHatch = 0;
		public static final double cargoPort = 41;

	}

	public class LimeLight {
		public static final double STEER_K = -0.02;                    // how hard to turn toward the target
        public static final double DRIVE_K = 0.02;                    // how hard to drive fwd toward the target
        public static final double DESIRED_TARGET_DISTANCE = 12.0;        // Area of the target when the robot reaches the wall
        public static final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast
	}
}
