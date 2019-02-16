package frc.robot.util.profiling;


public class Position extends Location {
	public double yaw;
	
	public Position(double x, double y, double yaw){
		super(x,y);
		this.yaw = yaw;
	}
	
	public Position(Location loc, double yaw) {
		super(loc.x, loc.y);
		this.yaw = yaw;
	}
	

	
}
