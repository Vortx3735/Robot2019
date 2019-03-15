package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

//Hatch intake
public class Carriage extends Subsystem {

	Solenoid solenoid;
	
	public Carriage() {
		//super("Carriage","David fix this");
		solenoid = new Solenoid(RobotMap.Carriage.solenoid);
	}
	public void setSolenoid(boolean b) {
		if(Robot.elevator.getPosition()>4.5) {
			solenoid.set(b);
		}
	}
	public void log() {
      SmartDashboard.putBoolean("Carriage Pnumatic", solenoid.get());
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	
}
