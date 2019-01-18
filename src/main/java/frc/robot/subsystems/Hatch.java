package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

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
