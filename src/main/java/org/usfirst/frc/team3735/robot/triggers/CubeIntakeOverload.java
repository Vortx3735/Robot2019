package org.usfirst.frc.team3735.robot.triggers;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.ComTrigger;
import org.usfirst.frc.team3735.robot.util.settings.Func;

public class CubeIntakeOverload extends ComTrigger{
	
	private Func maxPower;

	public CubeIntakeOverload(Func pow){
		this.maxPower = pow;
	}
	
	public CubeIntakeOverload(double pow){
		this(Func.getFunc(pow));
	}
	

	@Override
	public boolean get() {
		return Robot.cubeIntake.getPower() > maxPower.getValue();
	}

	@Override
	public String getHaltMessage() {
		return "The Intake motors exceeded " + maxPower.getValue() + " Joules.";
	}
	
	
	
	
}
