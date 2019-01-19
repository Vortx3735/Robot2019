package frc.robot.ois;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.assists.NavxAssist;
import frc.robot.commands.drive.movedistance.MoveDDx;
import frc.robot.commands.drive.positions.ResetPosition;
import frc.robot.commands.drive.positions.SetToLastPosition;
import frc.robot.commands.drive.recorder.RecordProfile;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.oi.DriveOI;
import frc.robot.util.oi.XboxController;

public class GTAOI implements DriveOI {

	public XboxController main;
	public XboxController co;

	public GTAOI() {
		main = new XboxController(0);
		co = new XboxController(1);
		

		
		/*************************************************
		 * Main-Driver
		 *************************************************/


		
		
		/*************************************************
		 * Co-Driver
		 *************************************************/

		
		
		
		
		/*************************************************
		 * Dash-board
		 *************************************************/
		SmartDashboard.putData("Reset Position", new ResetPosition());
		// SmartDashboard.putData("Reset Yaw", new ZeroYaw());
		SmartDashboard.putData(new MoveDDx(100, .6, .03).addA(new NavxAssist()));
		
//		SendProfile s = new SendProfile();
//		StringSetting loadFile= new StringSetting("Loading File", "defaultfile");
//		SmartDashboard.putData("Load File", new InstantCommand() {
//			@Override
//			protected void initialize() {
//				SendProfile.loadCommand(loadFile.getValue());
//			}
//			
//		});
//		SmartDashboard.putData(s);
		SmartDashboard.putData(new RecordProfile());
		SmartDashboard.putData(new SetToLastPosition());
	}
	
	
	public double getDriveMove() {
		return (main.getRightTrigger() - main.getLeftTrigger());
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		return main.getLeftX();
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
		//return VortxMath.handleDeadband(main.getRightY()+ co.getLeftY(), .05);
		return VortxMath.handleDeadband(co.getLeftY(), .05);
	}
	
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .4 || Math.abs(getDriveTurn()) > .4;
	}
	

	
	public double getIntakeMove() {
		return VortxMath.handleDeadband(co.getRightY(), .08);
	}
	
	public double getIntakeTwist() {
		return 0;//VortxMath.handleDeadband(co.getRightX(), .08);
	}

	public double getCarriageMove() {
		return .5 * VortxMath.handleDeadband(Math.pow(co.getRightTrigger(), 2) - Math.pow(co.getLeftTrigger(), 2), .08);
	}
	
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}


	public double getAnglerMove() {
//		return VortxMath.handleDeadband(co.getRightY(), .08) * .5;
		return 0;
	}




}
