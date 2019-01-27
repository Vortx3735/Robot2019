package frc.robot.triggers;

import frc.robot.Robot;
import frc.robot.util.cmds.ComTrigger;

public class HasReachedHeight extends ComTrigger{
	
	private Double targetSpeed;
	private boolean isLessThan = true;
	
	public HasReachedHeight(double spd){
		this.targetSpeed = spd;
	}

	@Override
	public void initialize() {
		isLessThan = evaluateSpeed();
	}
	
	public boolean evaluateSpeed() {
		return Robot.drive.getAverageSpeed() < targetSpeed;
	}
	@Override
	public boolean get() {
		return evaluateSpeed() != isLessThan;
	}

	@Override
	public String getHaltMessage() {
		return "Reached speed " + targetSpeed + "Inches/second";
	}
	
}
