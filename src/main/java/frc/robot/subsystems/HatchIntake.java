package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

//Hatch intake
public class HatchIntake extends Subsystem {

	Solenoid solenoid;
	public HatchIntake()
	{
		super();
		solenoid = new Solenoid(RobotMap.Hatch.solenoid);
	}
	public void setSolenoid(boolean b)
	{
		solenoid.set(b);
	}
	public void log()
    {
      SmartDashboard.putBoolean("ActivateHatchIntake", solenoid.get());
      
    }
	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
