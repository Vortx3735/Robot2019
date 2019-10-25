package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

//Hatch intake
public class HatchIntake extends Subsystem {

	public Solenoid solenoid;
	
	public HatchIntake() {
//		super("hatchIntake","HTC");
		solenoid = new Solenoid(RobotMap.Hatch.solenoid);
	}
	public void setSolenoid(boolean b)
	{
		solenoid.set(b);
		System.out.println("setSolenoid Called");
		System.out.println("Current state is: "+solenoid.get()+"\n");
	}
	public void log()  {
      SmartDashboard.putBoolean("Hatch Intake", solenoid.get());
      
    }
	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
