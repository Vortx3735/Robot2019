package frc.robot.util.recording;

import frc.robot.util.profiling.Position;

public class DriveState {

	public Position pos;
	public double left;
	public double right;
	
	public DriveState(Position p, double l, double r) {
		this.pos = p;
		this.left = l;
		this.right = r;
	}
	
	public String toString() {
		return Filer.make("Left", left, 3) + Filer.make("Right", right, 3) + pos.toString();
	}
	
	public static String getCSVHeader() {
		return "x,y,heading,left,right";
	}
	public String toCSV() {
		return String.format("%.4f,%.4f,%.4f,%.4f,%.4f", pos.x, pos.y, pos.yaw, left, right);
	}
	
	public static DriveState fromString(String s) {
		return new DriveState(
				Position.fromString(s),
				Filer.getDouble("Left", s),
				Filer.getDouble("Right", s)
		);
	}
	
	public void rotate(double d) {
		left += d;
		right -=d;
	}
	
	public double getMove() {
		return .5 * (left + right);
	}
	
	public double getTurn() {
		return (left - right) * .5;
	}
	
}
