package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.VortxSubsystem;

//Hatch intake
public class Carriage extends VortxSubsystem {

	Solenoid solenoid;
	
	public Carriage() {
		super("Carriage","David fix this");
		solenoid = new Solenoid(RobotMap.Carriage.solenoid);
	}
	public void setSolenoid(boolean b) {
		solenoid.set(b);
	}
	public void log() {
      SmartDashboard.putBoolean("Carriage Pnumatic", solenoid.get());
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
