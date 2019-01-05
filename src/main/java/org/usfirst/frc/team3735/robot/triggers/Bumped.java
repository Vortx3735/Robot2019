package org.usfirst.frc.team3735.robot.triggers;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.ComTrigger;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.Timer;

public class Bumped extends ComTrigger{
	
	private Func acc;
	private double delay = 0;
	private double initTime = 0;

	public Bumped(Func acc){
		this.acc = acc;
	}
	
	public Bumped(double acc){
		this(Func.getFunc(acc));
	}
	
	public Bumped(){
		this(1);
	}
	
	public void initialize() {
		initTime = Timer.getFPGATimestamp();
		
	}

	@Override
	public boolean get() {
		return Timer.getFPGATimestamp() > (initTime + delay) && Math.abs(Robot.navigation.getXYAcceleration()) > acc.getValue();
//		if(n) {
//			System.out.println(Robot.navigation.getXYAcceleration());
//		}
//		return n;
	}

	@Override
	public String getHaltMessage() {
		return "bumped " + acc + " acc";
	}
	
	public ComTrigger setDelay(double d) {
		delay = d;
		return this;
	}
	
	
	
	
}
