package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;

import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.GraphiteJoystick;
import org.usfirst.frc.team3735.robot.util.oi.GraysWheel;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

public class WheelOI implements DriveOI{

	GraysWheel wheel;
	GraphiteJoystick stick;
	XboxController box;
	
	public WheelOI(){
		wheel = new GraysWheel(0);
		stick = new GraphiteJoystick(1);
		box = new XboxController(2);
		

//		main.pov90.whileHeld(new DriveAddSensitiveRight());
//		main.pov270.whileHeld(new DriveAddSensitiveLeft());
		

		Setting carriageFine = new Setting("Carriage Fine Speed", .2);

		
//		box.pov90.whileHeld(new ElevatorCorrectRight());
//		box.pov270.whileHeld(new ElevatorCorrectLeft());
	}
	
	public double getDriveMove() {
		return stick.getGY();
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		return wheel.getGX();
		//return main.getRightX();
	}
	
	@Override
	public double getFODMag() {
		//return main.getRightMagnitude();
		return 0;
	}
	
	public double getFODAngle(){
		//return main.getRightAngle();
		return 0;
	}

	public double getElevatorMove() {
		return box.getRightY();
	}
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .4 || Math.abs(getDriveTurn()) > .4;
	}

	
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}
}
