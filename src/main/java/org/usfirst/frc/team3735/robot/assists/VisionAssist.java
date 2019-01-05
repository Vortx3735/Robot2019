package org.usfirst.frc.team3735.robot.assists;


import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.util.cmds.ComAssist;

public class VisionAssist extends ComAssist{
	
	private double prevWorking = 0;

	public VisionAssist(){
		requires(Robot.vision);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		Double in = Robot.vision.getRelativeCX();
    	if(in == null){
    		Robot.drive.setVisionAssist(0);
    	}else{
    		prevWorking = in;
        	Robot.drive.setVisionAssist(in * .0025);
    	}
	}
	
	
}
