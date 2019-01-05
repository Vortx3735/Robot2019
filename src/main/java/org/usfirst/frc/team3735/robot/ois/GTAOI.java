package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.EndAll;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;

import org.usfirst.frc.team3735.robot.commands.climber.ClimberSetSpeed;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotSetPID;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotReset;
import org.usfirst.frc.team3735.robot.commands.cubeintake.PivotSet;
import org.usfirst.frc.team3735.robot.commands.drive.DriveSetPID;
import org.usfirst.frc.team3735.robot.commands.drive.SwitchDirection;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.SetToLastPosition;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.RecordProfile;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorCorrect;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorResetPos;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosLgs;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosSetting;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.CubeTransfer;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.CarriageOverload;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GTAOI implements DriveOI{

	public XboxController main;
	public XboxController co;

	public GTAOI() {
		main = new XboxController(0);
		co = new XboxController(1);
		

		
		/*************************************************
		 * Main-Driver
		 *************************************************/
		Setting cubeintake = new Setting("Cube Intake Speed", -.7);

		main.a.whileHeld(new CarriageSetRoller(new Setting("Carriage Outtake Speed", .5)));
		main.a.whileHeld(new CubeSetRoller(cubeintake.multiply(new Setting("Cube Intake Slow Mult", .6)),cubeintake));
		main.a.whenPressed(new PivotSetPID(0, false));
		//main.a.whenReleased(new PivotSetPID(75, false));
		main.x.whileHeld(new CarriageSetRoller(new Setting("Carriage Intake Speed", -.5))
				.addT(new CarriageOverload(new Setting("Carriage Intake MaxPower", 30))));
		main.x.whileHeld(new CubeSetRoller(new Setting("Cube Transfer Speed", .7)));
		main.b.whileHeld(new CubeSetRoller(new Setting("Cube Outtake Speed", 1)));

//		main.y
		
//		Setting maxp = new Setting("Logis Max P", 1);
//		main.pov0.whenPressed(new TurnTo(0));
//		main.pov90.whenPressed(new TurnTo(90));
//		main.pov180.whenPressed(new TurnTo(180));
//		main.pov270.whenPressed(new TurnTo(270));
		
		main.pov0.whenPressed(new PivotSetPID(130, false));
		main.pov90.whenPressed(new PivotSetPID(70, false));
		main.pov180.whenPressed(new PivotSetPID(0, false));
		main.pov270.whenPressed(new CubeTransfer());
//		main.pov0.whileHeld(new PivotSet(.5));
//		main.pov180.whileHeld(new PivotSet(-.5));
		//SmartDashboard.putNumber("Reset Cube Encoder", new PivotReset());

		Setting spin = new Setting("Cube Spin Speed", .7);
//		main.lb.whileHeld(new CubeSetSols(true, false));
		main.lb.whileHeld(new CubeSetRoller(spin.reverse(), spin));
		main.rb.whileHeld(new CubeSetRoller(spin, spin.reverse()));
//		main.rb.whileHeld(new CubeSetSols(false, true));

		main.start.whileHeld(new PivotReset());
		//main.back.whenPressed(new ElevatorResetPos());
		main.back.whenPressed(new SwitchDirection());
//		main.back
		
		
		/*************************************************
		 * Co-Driver
		 *************************************************/
		Setting carriageFine = new Setting("Carriage Fine Speed", .3);
		co.a.whileHeld(new CarriageSetRoller(carriageFine.reverse()));
		co.b.whileHeld(new CarriageSetRoller(carriageFine));
		co.x.whileHeld(new CubeSetRoller(-.5));
		co.y.whileHeld(new ClimberSetSpeed(new Setting("Climb Speed", 1)));
		
		Setting elevatorTrim = new Setting("Elevator Trim", .3);
//		co.pov0
//		co.pov90.whileHeld(new ElevatorCorrect(elevatorTrim));
////		co.pov180
//		co.pov270.whileHeld(new ElevatorCorrect(elevatorTrim.reverse()));
		co.pov0.whenPressed(new PivotSetPID(130, false));
		co.pov90.whenPressed(new PivotSetPID(73, false));
		co.pov180.whenPressed(new PivotSetPID(0, false));
		co.pov270.whenPressed(new CubeTransfer());
		
		
		Setting carriageShoot = new Setting("Carriage Shoot Speed", 1);
		co.lt.whileHeld(new CarriageSetRoller(carriageShoot.reverse()));
		co.rt.whileHeld(new CarriageSetRoller(carriageShoot));
		co.rb.toggleWhenPressed(new CarriageRaiseTele());
		//co.rb.whileHeld(new PivotSet(.25));
		//co.lb.whileHeld(new PivotSet(-.25));
		
		co.start.whileHeld(new PivotReset());
		co.back.whenPressed(new ElevatorResetPos());

//		co.back
		
		
		
		/*************************************************
		 * Dash-board
		 *************************************************/
		SmartDashboard.putData("Reset Position", new ResetPosition());
//		SmartDashboard.putData("Reset Yaw", new ZeroYaw());
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
////		SmartDashboard.putData(s);
		SmartDashboard.putData(new RecordProfile());
		SmartDashboard.putData(new SetToLastPosition());
		
		Setting position = new Setting("Elevator Jamal Position", 0);
		SmartDashboard.putData(new ElevatorSetPosPID(position, false));
		SmartDashboard.putData(new ElevatorSetPosDDx(position, new Setting("Elevator DDx maxp", .7), new Setting("Elevator DDx acc", .03)));
		SmartDashboard.putData("Elevator Reset", new ElevatorResetPos());
		SmartDashboard.putData("Auto Right Scale Lineup", new AutoScaleLineup(true));
		SmartDashboard.putData("Auto Left Scale Lineup", new AutoScaleLineup(false));
		SmartDashboard.putData("Auto Right Switch Lineup", new AutoSwitchLineup(true));
		SmartDashboard.putData("Auto Left Switch Lineup", new AutoSwitchLineup(false));
//		SmartDashboard.putData(new PivotReset());
		SmartDashboard.putData(new PivotSetPID(new Setting("Cube Angler SetPos", 0), false));
		SmartDashboard.putData(new DriveSetPID());	

//		Setting position = new Setting("Position", 0);
//		PIDSetting setting = new PIDSetting(80, .15, 60, 0 ,0, 0, "PID Setting", true);
//		SmartDashboard.putData(new ElevatorSetPositionSetting(position, setting));
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
