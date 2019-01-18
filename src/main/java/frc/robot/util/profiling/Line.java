package org.usfirst.frc.team3735.robot.util.profiling;

import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.recording.Filer;

public class Line {
	
	public double m = 0;
	public double b = 0;
	
	public boolean isVertical = false;
	public double xOffset;
	
	public Line(Location p1, Location p2) {
		double dem = p2.x - p1.x;
		if(Math.abs(dem) < .0000001) {
			isVertical = true;
			xOffset = p1.x;
		}else {
			m = (p2.y-p1.y)/dem;
			b = -m * p1.x + p1.y;
		}
	}
	
	public Line(Location p1, double slope){
		m = slope;
		b = -m * p1.x + p1.y;
	}
	
	/**
	 * 
	 * @param loc	the point at which the perpendicular line will pass through
	 * @return		a line that is perpendicular to this, and goes through loc
	 */
	public Line getPerpendicular(Location loc) {
		if(m == 0) {
			return new Line(loc, new Location(loc.x, loc.y + 1));
		}else if(isVertical == true) {
			return new Line(loc, 0);
		}
		return new Line(loc, -1/m);
	}
	
	public double distanceFrom(Location loc) {
		if(!isVertical) {
			double x2 = (loc.y + (1/m)*loc.x - b) / (m + 1/m);
			double y2 = m*x2 + b;
			return loc.distanceFrom(new Location(x2,y2));
		}else {
			return Math.abs(loc.x - this.xOffset);
		}
	}
	
	/**
	 * 
	 * @return	angle in degrees
	 */
	public double getAngle() {
		return Math.toDegrees(-Math.atan(m)) + 90;
	}
	
	public double smallAngleWith(Line other) {
		double a = Math.abs(VortxMath.navLimit(getAngle() - other.getAngle()));
		if(a > 90) {
			return 180 - a;
		}else {
			return a;
		}
	}
	
	public double smallAngleWith(double yaw) {
		double a = Math.abs(VortxMath.navLimit(getAngle() - yaw));
		if(a > 90) {
			return 180 - a;
		}else {
			return a;
		}
	}
	
	public double func(double x) {
		return m * x + b;
	}
	
	public String toString() {
		return Filer.make("lineM", m, 3) + Filer.make("lineB", b, 3);
	}
}
