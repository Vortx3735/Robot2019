package frc.robot.settings;

//it stands for Dimensions if you haven't figured that out
public class Dms {
	//all in inches

	public class Bot{
		public class DriveBase{
			
			public static final double WIDTH = 30.5;
			public static final double HALFWIDTH = WIDTH/2.0;
			public static final double LENGTH = 18.5;
			public static final double HALFLENGTH = LENGTH/2.0;
		}
		public static final double WIDTH = 39.25;
		public static final double HALFWIDTH = WIDTH/2.0;
		public static final double LENGTH = 34.5;
		public static final double HALFLENGTH = LENGTH/2.0;
		
	}
	
	public class Field{
		//just guesses rn
		public static final double LENGTH = 653;
		public static final double HALFLENGTH = LENGTH/2.0;
		public static final double WIDTH = 324;
		public static final double HALFWIDTH = WIDTH/2.0;
		
		//width of the alliance wall, since field is an octagon
		public static final double WALLWIDTH = 264.0;
		public static final double HALFWALLWIDTH = WALLWIDTH/2.0;
		
		public static final double TOBASELINE = 93.3;

	}
	
	
	
	
//	  if (rotateValue * moveValue > 0.0) {
//	    leftMotorSpeed = Math.signum(moveValue) * (Math.abs(moveValue) - Math.abs(rotateValue));
//	    rightMotorSpeed = Math.signum(moveValue) * Math.max(Math.abs(moveValue), Math.abs(rotateValue));
//	  } else {
//	    leftMotorSpeed = Math.signum(moveValue) * Math.max(Math.abs(moveValue), Math.abs(rotateValue));
//	    rightMotorSpeed = Math.signum(moveValue) * (Math.abs(moveValue) - Math.abs(rotateValue));
//	  }
	    

	
}
