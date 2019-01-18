package frc.robot.triggers;

import frc.robot.util.cmds.ComTrigger;

public class DriveOverride extends ComTrigger{
	
	
	public DriveOverride(){
	}

	@Override
	public boolean get() {
		return false;// Robot.oi.isOverriddenByDrive();
	}
	
	@Override
	public String getHaltMessage() {
		return "overriden by drive";
	}
	
	
}
