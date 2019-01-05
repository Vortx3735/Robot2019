package org.usfirst.frc.team3735.robot.assists;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Navigation;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.cmds.ComAssist;

public class NavxVisionAssist extends ComAssist{

	private double prevWorking = 0;

	public NavxVisionAssist(){
    	prevWorking = 0;
    	requires(Robot.vision);
    	requires(Robot.navigation);
	}
	
	@Override
	public void initialize() {
		prevWorking = 0;
	}

	@Override
	public void execute() {
		Double input = Robot.vision.getRelativeCX();
    	if(input != null){
    		if(input != prevWorking){
		    	Robot.navigation.getController().setSetpoint(
		    			VortxMath.continuousLimit(
	        				Robot.navigation.getYaw() + (input * Robot.vision.dpp.getValue()),
	        				-180, 180)
				);
				prevWorking = input;
			}
    		Robot.drive.setVisionAssist((Robot.navigation.getController().getError()/180.0) * Navigation.navVisCo.getValue());
    	}else{
    		Robot.drive.setVisionAssist(0);
    	}
	}
	
	@Override
	public void end(){
		Robot.drive.setVisionAssist(0);
	}
	
//doy mun fuh = ???
//sing chow em = hello
//em depp
	
}
