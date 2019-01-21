package frc.robot;

public class Constants {

	public static final double dt = .05; //seconds
	
	public class Drive {
		public static final double InchesPerRotation = 18;
		public static final double maxVelocity = 1.5; //m/s
		public static final double maxAccel = 2; //m/s^2
		public static final double maxJerk = 60; //m/s^3
		public static final double wheelBase = 0.5; //m
		public static final double wheelDiam = .2; //m
		public static final int ticksPerRotation = 1023;
	}

	public class Jevois {
		public static final double focalLength = 0.0;
		public static final double fieldOfVision = 0.0;
	}

	public class PathFinder {

	}
}
