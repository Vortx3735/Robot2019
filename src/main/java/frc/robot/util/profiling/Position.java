package org.usfirst.frc.team3735.robot.util.profiling;

import java.io.Serializable;

import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.recording.Filer;

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
	
	public String toString() {
		return Filer.make("Yaw", yaw, 1) + super.toString();
	}
	
	public static Position fromString(String s) {
		 return new Position(
				 Location.fromString(s), 
				 Filer.getDouble("Yaw", s)
		 );
	}
	

	
	
}
