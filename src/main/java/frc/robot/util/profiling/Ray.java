package frc.robot.util.profiling;

import frc.robot.util.recording.Filer;
import frc.robot.util.calc.VortxMath;

public class Ray extends Line{
	public double yaw;

	public Ray(Location from, Location to) {
		super(from, to);
		yaw = Math.toDegrees(Math.atan2(to.x - from.x, to.y - from.y ));
	}
	
	public Ray(Location loc, double yaw) {
		super(loc, Math.tan(Math.toRadians(90 - yaw)));
		this.yaw = yaw;
	}
	
	public double angleTo(Ray other) {
		return VortxMath.navLimit(other.yaw - yaw);
	}
	
	public double angleTo(Position pos) {
		return VortxMath.navLimit(pos.yaw - yaw);
	}
	/**
	 * 
	 * @param other
	 * @return	the distance from a location, to the line. Designed with error correcting in mind.
	 */
	@Override
	public double distanceFrom(Location other) {
		double d = super.distanceFrom(other);
		if(super.isVertical) {
			return Math.signum(super.xOffset - other.x) * d;
		}else {
			double dy = super.func(other.x) - other.y;
			if(dy > 0) {
				if(yaw > 0) {
					return -d;
				}else {
					return d;
				}
			}else {
				if(yaw > 0) {
					return d;
				}else {
					return -d;
				}
			}
		}
		
	}
	
	public String toString() {
		return Filer.make("LYaw", yaw, 2) + super.toString();
	}
}
