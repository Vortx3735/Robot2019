package org.usfirst.frc.team3735.robot.triggers;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.ComTrigger;
import org.usfirst.frc.team3735.robot.util.settings.Func;

public class CarriageOverload extends ComTrigger{
	
	private Func maxPower;

	public CarriageOverload(Func pow){
		this.maxPower = pow;
	}
	
	public CarriageOverload(double pow){
		this(Func.getFunc(pow));
	}
	

	@Override
	public boolean get() {
		return Robot.carriage.getPower() > maxPower.getValue();
	}

	@Override
	public String getHaltMessage() {
		return "The Carriage motor exceeded " + maxPower.getValue() + " Joules.";
	}
	
	
	
	
}
