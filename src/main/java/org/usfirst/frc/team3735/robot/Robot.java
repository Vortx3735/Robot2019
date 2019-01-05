package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.auto.*;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.SetToLastPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ZeroYaw;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.RecordProfile;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorResetPos;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.ois.GTAOI;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.subsystems.Carriage;
import org.usfirst.frc.team3735.robot.subsystems.Climber;
import org.usfirst.frc.team3735.robot.subsystems.Pivot;
import org.usfirst.frc.team3735.robot.subsystems.CubeIntake;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.subsystems.LEDS;
import org.usfirst.frc.team3735.robot.subsystems.LEDS.Data;
import org.usfirst.frc.team3735.robot.subsystems.Navigation;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.util.bases.VortxIterative;
import org.usfirst.frc.team3735.robot.util.choosers.AutoChooser;
import org.usfirst.frc.team3735.robot.util.choosers.DoNothing;
import org.usfirst.frc.team3735.robot.util.choosers.Side;
import org.usfirst.frc.team3735.robot.util.choosers.SideChooser;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.profiling.Position;
import org.usfirst.frc.team3735.robot.util.settings.BooleanSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends VortxIterative {

	
	public static Drive drive;
	public static Navigation navigation;
	public static Vision vision;
	public static CubeIntake cubeIntake;
	public static Elevator elevator;
	public static Climber climber;
	public static Carriage carriage;
	public static Pivot pivot;
	
	public static Autonomous autoLogic;
	
	public static GTAOI oi;
	
	
	public static SideChooser sideChooser;
	public static AutoChooser autoChooser;
	
	LEDS leds = new LEDS();
	

	PowerDistributionPanel pdp;
	
	
	@Override
	public void robotInit() {
		drive = new Drive();
		navigation = new Navigation();
		vision = new Vision();
		pivot = new Pivot();

		cubeIntake = new CubeIntake();
		elevator = new Elevator();
		climber = new Climber();
		carriage = new Carriage();

		oi = new GTAOI(); //MUST be instantiated after the subsystems
			
		autoLogic = new Autonomous();
		/*
		 * Selections:
		 * Side: Red, Blue
		 * Starting Position: Left, Mid, Right
		 * Priority: Scale, Switch, Line
		 * Complexity: Strict, Curved
		 * 
		 */
		 
		boolean complex = true;
		autoChooser = new AutoChooser();
			autoChooser.addObject("Left Scale Left", new LeftScaleLeft(complex));
			autoChooser.addObject("Left Scale Right", new LeftScaleRight(complex));
			autoChooser.addObject("Left Switch Left", new LeftSwitchLeft(complex));
			autoChooser.addObject("Mid Switch Left", new MidSwitchLeft(complex));
			autoChooser.addObject("Mid Switch Right", new MidSwitchRight(complex));
			autoChooser.addObject("Right Scale Left", new RightScaleLeft(complex));
			autoChooser.addObject("Right Scale Right", new RightScaleRight(complex));
			autoChooser.addObject("Right Switch Right", new RightSwitchRight(complex));
			autoChooser.addObject("Drive Forward", new UnknownStraight());
			
			
		sideChooser = new SideChooser();
//			posChooser.addDefault("Left", new Position(-Dms.Field.HALFWALLWIDTH + Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 0));
//			posChooser.addDefault("Middle", new Position(0, Dms.Bot.HALFLENGTH, 0));
//			posChooser.addDefault("Right", new Position(Dms.Field.HALFWALLWIDTH - Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 0));

		SmartDashboard.putData("Autonomous Testing", autoChooser);
		SmartDashboard.putData("Side", sideChooser);

		
		pdp = new PowerDistributionPanel();
//		SmartDashboard.putNumber("Talon 0", pdp.getCurrent(0));
//		SmartDashboard.putNumber("Talon 1", pdp.getCurrent(1));
//		SmartDashboard.putNumber("Talon 2", pdp.getCurrent(2));
//		SmartDashboard.putNumber("Talon 3", pdp.getCurrent(3));
//		SmartDashboard.putNumber("Talon 4", pdp.getCurrent(4));
//		SmartDashboard.putNumber("Talon 5", pdp.getCurrent(5));
//		SmartDashboard.putNumber("Talon 6", pdp.getCurrent(6));	
//		SmartDashboard.putNumber("Talon 7", pdp.getCurrent(7));
//		SmartDashboard.putNumber("Talon 8", pdp.getCurrent(8));
//		SmartDashboard.putNumber("Talon 9", pdp.getCurrent(9));
		//SmartDashboard.putNumber("PDP 10", pdp.getCurrent(10));
		//SmartDashboard.putNumber("PDP 11", pdp.getCurrent(11));
//		SmartDashboard.putNumber("Talon 12", pdp.getCurrent(12));
		

//		SmartDashboard.putData(new TurnTo(new Setting("Turning Setpoint")));

		
	}
	@Override
	public void robotPeriodic() {		
		Setting.fetchAround();
		BooleanSetting.fetchAround();
		
//        vision.debugLog();
//        navigation.integrate();
        navigation.displayPosition();
//        drive.debugLog();
        if(DriverStation.getInstance().getMatchTime() > 120) {
//        	leds.sendData(Data.);
        }
        
//        System.out.println("Talon 0:  " + pdp.getCurrent(0));
//        System.out.println("Talon 1:  " + pdp.getCurrent(1));
//        System.out.println("Talon 2:  " + pdp.getCurrent(2));
//        System.out.println("Talon 3:  " + pdp.getCurrent(3));
//        System.out.println("Talon 4:  " + pdp.getCurrent(4));
//        System.out.println("Talon 5:  " + pdp.getCurrent(5));
//        System.out.println("Talon 6:  " + pdp.getCurrent(6));
//        System.out.println("Talon 7:  " + pdp.getCurrent(7));
//        System.out.println("Talon 8:  " + pdp.getCurrent(8));
//        System.out.println("Talon 9:  " + pdp.getCurrent(9));
//        System.out.println("PDP 10:  " + pdp.getCurrent(10));
//        System.out.println("PDP 11: " + pdp.getCurrent(11));
//        System.out.println("Talon 12: " + pdp.getCurrent(12));
        
        log();    
        debugLog();
	}
	@Override
	public void robotContinuous() {
		navigation.integrate();		
	}
	
	

	@Override
	public void autonomousInit() {	
		navigation.resetAhrs();
		navigation.resetPosition(autoLogic.getStartingPosition());
		navigation.PIDAuto();
		elevator.resetEncoderPositions();
//		pivot.resetInside();
		System.out.println("Choosing Auto");
		
		autoLogic.chooseAutonomous();
		autoLogic.printAuto();
//		autoLogic.startCommand();
		autoLogic.startCommand();
//		autoChooser.startSelected();
		leds.SendDataAutonomous();

		
	}
	@Override
	public void autonomousPeriodic() {
		 Scheduler.getInstance().run();
//		 vision.refresh();

	}
	@Override
	public void autonomousContinuous() {
		
	}
	
	

	@Override
    public void teleopInit() {
        autoChooser.cancel();
        autoLogic.cancel();
        navigation.PIDNormal();
        if(sideChooser.getSelected() == Side.RED) {
        	leds.sendData(Data.REDFIRE);
        }else {
        	leds.sendData(Data.BLUEFIRE);
        }

        
    }
	@Override
	public void teleopPeriodic() {
        Scheduler.getInstance().run();
	}
	@Override
	public void teleopContinuous() {

	}
	
	

	@Override
	public void testPeriodic() {
	}
	@Override
	public void disabledInit() {
		autoChooser.cancel();
	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}
	@Override
	public void disabledContinuous() {
		
	}
	
	
	
	public void log(){
		drive.log();
		navigation.log();
//		vision.log();
		elevator.log();
//		carriage.log();
		//carriage.debugLog();
		pivot.log();
		
     
	}
	
	public void debugLog(){
//		drive.debugLog();
//		navigation.debugLog();
		elevator.debugLog();
//		vision.debugLog();
		
	}
	


}