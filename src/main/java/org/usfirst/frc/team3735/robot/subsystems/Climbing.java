package org.usfirst.frc.team3735.robot.subsystems;


import org.usfirst.frc.team3735.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climbing extends Subsystem {

	Solenoid l1, l2, r1, r2;
	
	public Climbing() {
		super();
		l1 = new Solenoid(RobotMap.Climbing.solenoidL1);
		//l2 = new Solenoid(RobotMap.Climbing.solenoidL2);
		r1 = new Solenoid(RobotMap.Climbing.solenoidR1);
		//r2 = new Solenoid(RobotMap.Climbing.solenoidR2);
	}
	
//
	
	public void setSolenoids(boolean b) {
		l1.set(b);
		//l2.set(b);
		r1.set(b);
		//r2.set(b);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
