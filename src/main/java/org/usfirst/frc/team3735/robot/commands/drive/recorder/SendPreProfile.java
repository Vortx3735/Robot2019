package org.usfirst.frc.team3735.robot.commands.drive.recorder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.util.calc.Average;
import org.usfirst.frc.team3735.robot.util.calc.RollingAverage;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.motion.MotionData;
import org.usfirst.frc.team3735.robot.util.motion.MotionProfile;
import org.usfirst.frc.team3735.robot.util.motion.MotionSet;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.MissingColumnException;
import org.usfirst.frc.team3735.robot.util.profiling.Line;
import org.usfirst.frc.team3735.robot.util.profiling.Ray;
import org.usfirst.frc.team3735.robot.util.recording.DriveState;
import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SendPreProfile extends Command {
	

	private String file;
	
    double forwardLook = 0; //	degrees/180
    double angleLook = 0;	// 	degrees/180
    double angleError = 0;	//	degrees/180
    double distError = 0;	//	inches
    
	private ArrayList<DriveState> arr;
	private MotionSet data;
	int index;
    DriveState curState;
    Ray toFollow;
    boolean error;
	
	private static int lookAmount = 25; //.5 seconds
	private static Setting forwardLookCo = new Setting("Forward Look Co", .9);
//	private static Setting angleLookCo = new Setting("Angle Look Co", 0);
	private static Setting angleErrorCo = new Setting("Angle Error Co", 3.7);
//	private static Setting distErrorCo = new Setting("Dist Error Co", 0);
	private static Setting halfWay = new Setting("Half Way Co", 25);

	Average roll;
	
    public SendPreProfile(String file, boolean rev) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.file = file;
    	try {
			data = MotionProfile.builder().withProfileName(file).withProfilesFromJar().make();
//			MotionProfile.builder().withProfileName(file).withProfilesFromFilesystem().make();

		} catch (IOException | MissingColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}
    	if(data != null) {
			if(rev) {
				arr = data.reverseList();
			}else {
				arr = data.list();
			}
    	}
    	
    	
    	requires(Robot.drive);
    	requires(Robot.navigation);
    }
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	index = 0;
    	
    	roll = new Average();
    	
    	if(file!=null)System.out.println("Starting profile " + file);
    	//bring to closest point
//		while(Robot.navigation.getPosition().distanceFrom(arr.get(index).pos) > 
//			Robot.navigation.getPosition().distanceFrom(arr.get(limitIndex(index+1)).pos)){
//			index++;
//		}
    }
    



    
    protected void execute() {
		curState = arr.get(limitIndex(arr, index));
		//draw line between current point and previous point
		if(index != 0) {
	    	toFollow = new Ray(curState.pos, arr.get(index - 1).pos);
		}else {
			toFollow = new Ray(arr.get(index + 1).pos, curState.pos);
		}
		//set controller to the correct angle
		Robot.navigation.getController().setSetpoint(curState.pos.yaw);
		
		
		
		//if forward and angle look is available, calculate it
		if(limitIndex(arr, index + lookAmount) == (index + lookAmount)){
			//compute lookahead error
			Ray r = new Ray(Robot.navigation.getPosition(), arr.get(limitIndex(arr, index + lookAmount)).pos);
			forwardLook = Robot.navigation.getRay().angleTo(r)/ 180.0;
			//System.out.println("Robot Angle: " + Robot.navigation.getRay().yaw + "Target Ray Angle: " + r.yaw);
			//compute lookahead by indexing the angle of i+lookahead?
			//angleLook = Robot.navigation.getRay().angleTo(arr.get(index + lookAmount).pos) / 180.0;
			if(Robot.navigation.getPosition().distanceFrom(arr.get(limitIndex(arr, index + lookAmount)).pos) < 10) {
				forwardLook = 0;
			}
				
		}else {
			forwardLook = 0;
			angleLook = 0;
		}
//		System.out.println("Forward Look: " + forwardLook);

		//computer angle error
		angleError = VortxMath.navLimit(Robot.navigation.getController().getError()) / 180.0;
		//System.out.println("Angle Error: " + angleError);
		//compute distance from profile error
		distError = toFollow.distanceFrom(Robot.navigation.getPosition());
		
		
		roll.add(distError);
//		double turn = angleError * angleErrorCo.getValue() + forwardLook * lookCo.getValue() + distError * distErrorCo.getValue() + angleLook * angleLookCo.getValue();
		
		double p = Math.abs(VortxMath.squish(distError, halfWay.getValue()));
		//System.out.println("P: " + p);

		double turn =  p * forwardLook * forwardLookCo.getValue() +  (1-p) * angleError * angleErrorCo.getValue();
//		if(Double.isNaN(turn)) {
//			System.out.print("forward look" + forwardLook + "\t\t\t");
//			System.out.println("lookco" + forwardLookCo.getValue() + "\t\t");
//			
//		}
		
//		Robot.drive.normalDrive(curState, turn);
		Robot.drive.setLeftVelocity(curState.left + Drive.percentToSpeed(turn));
		Robot.drive.setRightVelocity(curState.right - Drive.percentToSpeed(turn));

//		Robot.drive.setLeftVelocity(left + turn * 10);
//		System.out.println("Move: " + curState.getMove() + "\t\t\t Turn: " + turn);
//		System.out.print("forward look" + forwardLook + "\t\t\t");
//		System.out.println("lookco" + forwardLookCo.getValue() + "\t\t");
    	index++;
    	System.out.println(Robot.navigation.getPosition().distanceFrom(curState.pos));

    	SmartDashboard.putNumber("Average Dist Error", roll.getAverage());
    }
    
    public int limitIndex(ArrayList<DriveState> a, int i) {
    	return (i < a.size()) ? i : a.size() - 1;
    }
    
    public synchronized ArrayList<DriveState> getArray(){
    	return arr;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return index >= getArray().size();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    

}
