package org.usfirst.frc.team3735.robot.subsystems;


import org.usfirst.frc.team3735.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hatch extends Subsystem {

	Solenoid solenoid;
	public Hatch()
	{
		super();
		solenoid = new Solenoid(RobotMap.Hatch.solenoid);
	}
	
//
	
	public void setSolenoid(boolean b)
	{
		solenoid.set(b);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
