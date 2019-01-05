package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.auto.*;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.SetToLastPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ZeroYaw;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.RecordProfile;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;

import org.usfirst.frc.team3735.robot.ois.GTAOI;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
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

	
	public static Autonomous autoLogic;
	
	public static GTAOI oi;
	
	//test101
	public static SideChooser sideChooser;
	public static AutoChooser autoChooser;
	
	LEDS leds = new LEDS();
	

	PowerDistributionPanel pdp;
	
	
	@Override
	public void robotInit() {
		drive = new Drive();
		navigation = new Navigation();
		vision = new Vision();

		oi = new GTAOI(); //MUST be instantiated after the subsystems
			
		autoLogic = new Autonomous();
		 
		sideChooser = new SideChooser();

		SmartDashboard.putData("Autonomous Testing", autoChooser);
		SmartDashboard.putData("Side", sideChooser);

		
		pdp = new PowerDistributionPanel();

		

//		SmartDashboard.putData(new TurnTo(new Setting("Turning Setpoint")));

		
	}
	@Override
	public void robotPeriodic() {
		Setting.fetchAround();
		BooleanSetting.fetchAround();
		
        navigation.displayPosition();
         
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
		System.out.println("Choosing Auto");
		autoLogic.startCommand();
		leds.SendDataAutonomous();

		
	}
	@Override
	public void autonomousPeriodic() {
		 Scheduler.getInstance().run();
	}
	@Override
	public void autonomousContinuous() {
		
	}
	
	

	@Override
    public void teleopInit() {
        autoChooser.cancel();
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
		vision.log();
	}
	
	public void debugLog(){
		drive.debugLog();
		navigation.debugLog();
		vision.debugLog();
	}
	


}