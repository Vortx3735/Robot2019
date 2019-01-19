package frc.robot.settings;

import frc.robot.util.profiling.Location;
import frc.robot.util.profiling.Position;
import frc.robot.util.recording.DriveState;

public class Waypoints {

	public static class Verticies{
		public static final Location center = new Location(Dms.Field.HALFLENGTH, 0);
		public static final Location topRight = new Location(Dms.Field.LENGTH, Dms.Field.HALFWIDTH);
		public static final Location topLeft = new Location(0, Dms.Field.HALFWIDTH);
		public static final Location bottomRight = new Location(Dms.Field.LENGTH, -Dms.Field.HALFWIDTH);
		public static final Location bottomLeft = new Location(0, -Dms.Field.HALFWIDTH);
	}
	
	public static class Pieces{
		public static final Location scaleLeft = new Location(-75, Dms.Field.HALFLENGTH);
		public static final Location scaleRight = new Location(75, Dms.Field.HALFLENGTH);
		public static final Location switchLeft = new Location(-70, 168); //abs center is 54
		public static final Location switchRight = new Location(64, 168);
		public static final Location switchLineupLeft =  new Location(-62, 78);
		public static final Location switchLineupRight =  new Location(62, 78);

		
		public static final Location headCube = new Location(0, 105);
		
	}

	public static class Auto{
		public static String leftScaleLeft = "leftScaleLeft";
		public static String leftScaleLeft2 = "leftScaleLeft2";
		public static String leftScaleLeft3 = "leftScaleLeft3";
		public static String leftScaleLeft4 = "leftScaleLeft4";
		public static String leftScaleLeft5 = "leftScaleLeft5";


		public static String leftScaleRight = "leftScaleRight";
		public static String leftScaleRight2 = "leftScaleRight2";
		public static String leftScaleRight3 = "leftScaleRight3";
		public static String leftScaleRight4 = "leftScaleRight4";
		public static String leftScaleRight5 = "leftScaleRight5";

		public static String leftSwitchLeft = "leftSwitchLeft";
		public static String leftSwitchLeft2 = "leftSwitchLeft2";
		public static String leftSwitchLeft3 = "leftSwitchLeft3";
		public static String leftSwitchLeft4 = "leftSwitchLeft4";
		public static String leftSwitchLeft5 = "leftSwitchLeft5";


		
		public static String midSwitchRight = "midSwitchRight";
		public static String midSwitchRight2 = "midSwitchRight2";
		public static String midSwitchRight3 = "midSwitchRight3";
		public static String midSwitchRight4 = "midSwitchRight4";
		public static String midSwitchRight5 = "midSwitchRight5";

		public static String midSwitchLeft = "midSwitchLeft";
		public static String midSwitchLeft2 = "midSwitchLeft2";
		public static String midSwitchLeft3 = "midSwitchLeft3";
		public static String midSwitchLeft4 = "midSwitchLeft4";
		public static String midSwitchLeft5 = "midSwitchLeft5";
		
		
		public static String midScaleLeft = "midScaleLeft";
		public static String midScaleRight = "midScaleRight";



		
		public static String rightScaleLeft = "rightScaleLeft";
		public static String rightScaleLeft2 = "rightScaleLeft2";
		public static String rightScaleLeft3 = "rightScaleLeft3";
		public static String rightScaleLeft4 = "rightScaleLeft4";
		public static String rightScaleLeft5 = "rightScaleLeft5";

		public static String rightScaleRight = "rightScaleRight";
		public static String rightScaleRight2 = "rightScaleRight2";
		public static String rightScaleRight3 = "rightScaleRight3";
		public static String rightScaleRight4 = "rightScaleRight4";
		public static String rightScaleRight5 = "rightScaleRight5";


		public static String rightSwitchRight = "rightSwitchRight";
		public static String rightSwitchRight2 = "rightSwitchRight2";
		public static String rightSwitchRight3 = "rightSwitchRight3";
		public static String rightSwitchRight4 = "rightSwitchRight4";
		public static String rightSwitchRight5 = "rightSwitchRight5";

	}

	
	public static class CSVAuto{
		public static DriveState[] leftScaleLeft;
		public static DriveState[] leftScaleRight;
		
		public static DriveState[] midSwitchLeft;
		public static DriveState[] midSwitchRight;
		
//		public static DriveState[] leftScaleLeft;
//		public static DriveState[] leftScaleRight;


		
	}
	//public static String rightScaleRight = "RightScaleRight";

	public static class Starting{
		public static final Position left = new Position(-Dms.Field.HALFWALLWIDTH, Dms.Bot.HALFLENGTH, 0);
		public static final Position mid = new Position(0, Dms.Bot.HALFLENGTH, 0);
		public static final Position right = new Position(Dms.Field.HALFWALLWIDTH, Dms.Bot.HALFLENGTH, 0);
		public static final Position unknown = mid;
	}

}
