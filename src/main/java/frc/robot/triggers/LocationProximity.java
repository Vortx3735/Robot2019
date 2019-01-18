package org.usfirst.frc.team3735.robot.triggers;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.ComTrigger;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.settings.Func;

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
