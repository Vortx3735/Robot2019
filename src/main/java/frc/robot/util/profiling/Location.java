package frc.robot.util.profiling;

import java.util.ArrayList;

public class Location{
	public double x,y;
	private static ArrayList<Location> staticLocations = new ArrayList<Location>();
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(double x, double y, boolean flag) {
		this(x,y);
		if(flag)staticLocations.add(this);
	}
	
	public Location appendYawDistance(double yaw, double distance) {
		double ang = Math.toRadians(-yaw);
		return new Location(x + Math.cos(ang), y + Math.sin(ang));
	}
	
	public Location appendXY(double addX, double addY) {
		return new Location(x + addX, y + addY);
	}
	
	public double distanceFrom(Location other) {
		return Math.hypot(this.x - other.x, this.y - other.y);
	}
	
	public double yawTo(Location other) {
		return Math.toDegrees(Math.atan2(other.x - x, other.y - y));
	}
	
}
