package frc.robot;

public class Constants {

	public static final double dt = Robot.kDefaultPeriod; //seconds
	
	public class Drive {

		//TODO: testing on these values
		public static final double maxVelocity = 80;//in/s  //TODO: testing
		public static final double maxAccel = 50;//in/s^2
		public static final double maxJerk = 300; //in/s^3

		public static final double wheelBase = 29; //in
		//if this is on a different gear then we should spin the wheel ten times and find the displacement
		public static final int ticksPerRotation = 5358; //ticks per rotation of wheels calculated
		//would be these if we were one to one1024; //4096 for mag encoder /1024 for grayhills we are using
		public static final double InchesPerRotation = 6*Math.PI;


		//54.7725 ticks per inch
		public static final double InchesPerTick = InchesPerRotation/ticksPerRotation;
		//.018257337;//.018718924;	//in inches (jamal)

		//exp drive
		public static final double moveReactivity = 1;	//(0,1] (least reactive, most reactive]
		public static final double turnReactivity = 1;	//(0,1] (least reactive, most reactive]	
		public static final double scaledMaxMove = .5;
		public static final double scaledMaxTurn = .5;	//(0,1] directly to the arcadedrive turn value
		//these retain the range but shift more of the action towards lower values as the exponent is raised higher
		//graph y = x * x^(p-1) {-1 < x < 1} for visualization
		public static final double moveExponent = 3;		//[1,inf) 1 is linear, 2 is squared (normal), etc.
		public static final double turnExponent = 3;		//[1,inf) 

		//for turning slowly with lb and rb
		public static final double lowSensitivityLeftTurn = -.3;
		public static final double lowSensitivityRightTurn = .3;
		
		public static final boolean isUsingLeftEncoders = true;
		public static final boolean isUsingRightEncoders = true;

		public static final double driveTolerance = 2;

		public static final double fastMaxMove = 1;
		public static final double fastMaxTurn = .7;

	}

	public class Elevator {
		//TODO set these values
		public static final double inchesPerTick = 0.020424788;
//		0.02192;
		public static final double tickPerRoatation = 512;
		//All these are heights and should assume the 0 is the bottom of the elevator
		public static final double lowRocketHatch = 11.5;
		public static final double midRocketHatch = 48;
		public static final double highRocketHatch = 91;
		public static final double lowRocketCargo = 0;
		public static final double midRocketCargo = 45;
		public static final double highRocketCargo = 86; 
		public static final double cargoHatch = 0;
		public static final double shipPort = 0;
		public static final double frameHeight = 10;

	}
	
	public class LimeLight {
		public static final double STEER_K = 0.05;                    // how hard to turn toward the target
        public static final double DRIVE_K = 0.025;                   // how hard to drive fwd toward the target
		public static final double MAX_DRIVE = 0.5;                   // Simple speed limit so we don't drive too fast
		public static final double MAX_TURN = 0.5;
		public static final double MIN_TURN = -0.5;
		public static final double ANGLE_OFFSET = .9; // was 1.8
		public static final double DISTANCE_OFFSET = 11.0;
	}

	public class BallArms {
		public static final double INTAKING = 451.0;
		public static final double STARTING = 0.0;
		public static final double SCORING = 0.0;
		public static final double CONSPOWERSTART = .20;

	}
}
