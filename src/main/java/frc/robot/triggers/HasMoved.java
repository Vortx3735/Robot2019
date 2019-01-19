package frc.robot.triggers;

import frc.robot.Robot;
import frc.robot.settings.Constants;
import frc.robot.util.calc.Integrator;
import frc.robot.util.cmds.ComTrigger;
import frc.robot.util.settings.Func;


public class HasMoved extends ComTrigger{
	
	private Func deltaDistance;
	private Double rsd;
	private Double lsd;
	
	private boolean isIntegrating = false;
	private Integrator integrator;
	
	public HasMoved(Func distance){
		this.deltaDistance = distance;
	}
	
	public HasMoved(double distance){
		this(Func.getFunc(distance));
	}

	@Override
	public void initialize() {
		if(Constants.Drive.isUsingLeftEncoders) {
			lsd = Robot.drive.getLeftPosition();
		}
		if(Constants.Drive.isUsingRightEncoders) {
			rsd = Robot.drive.getRightPosition();
		}
		if(lsd == null && rsd == null) {
			isIntegrating = true;
			integrator = new Integrator(0);
			integrator.init(Robot.drive.getSpeedFromCurrent());
		}

	}
	
	@Override
	public void execute() {
		if(isIntegrating) {
			integrator.feed(Robot.drive.getSpeedFromCurrent());
		}
	}
	
	@Override
	public boolean get() {
		if(deltaDistance.getValue() > 0){
			return distanceTraveled() > deltaDistance.getValue();
		}else{
			return distanceTraveled() < deltaDistance.getValue();
		}
	}


	/*
	 * the distance travelled, which is signed to indicated direction
	 */
	public double distanceTraveled(){
		if((rsd != null) && (lsd != null)) {
			return .5 * ((Robot.drive.getLeftPosition() - lsd) + (Robot.drive.getRightPosition() - rsd));
		}
		if(lsd != null) {
			return Robot.drive.getLeftPosition() - lsd;
		}
		if(rsd != null) {
			return Robot.drive.getRightPosition() - lsd;
		}
		if(integrator != null) {
			return integrator.total;
		}
		System.out.println("Has Moved Error: distanceTraveled() called but not initialized");
		return 0;
		
	}
	
	public double distanceToGo(){
		return deltaDistance.getValue() - distanceTraveled();
	}
	
	public double distance(){
		return deltaDistance.getValue();
	}
	
	@Override
	public String getHaltMessage() {
		return "Moved " + deltaDistance.getValue() + " Inches";
	}
	
	
}
