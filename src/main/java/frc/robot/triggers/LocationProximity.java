package frc.robot.triggers;

import frc.robot.Robot;
import frc.robot.util.cmds.ComTrigger;
import frc.robot.util.profiling.Location;

public class LocationProximity extends ComTrigger{
	
	private Location loc;
	private double dist;

	public LocationProximity(Location loc, double dist){
		this.loc = loc;
		this.dist = dist;
	}
	

	@Override
	public boolean get() {
		return loc.distanceFrom(Robot.navigation.getPosition()) < dist;
	}

	@Override
	public String getHaltMessage() {
		return "Robot is within" + dist + " inches of waypoint.";
	}
	
	
	
	
}
