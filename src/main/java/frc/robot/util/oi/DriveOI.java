package frc.robot.util.oi;

public interface DriveOI {
	
	public double getDriveMove();
	public double getDriveTurn();
	
	public double getFODMag();
	public double getFODAngle();

	public boolean isOverriddenByDrive();
	
	public void log();
	
}
