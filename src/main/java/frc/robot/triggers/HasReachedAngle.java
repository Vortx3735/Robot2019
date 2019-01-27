package frc.robot.triggers;

import frc.robot.Robot;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.cmds.ComTrigger;

public class HasReachedAngle extends ComTrigger{
	
	private Double targetAngle;
	private double startAngle;
	private Double deltaAngle;
	private boolean isRelative;

	
	public HasReachedAngle(double angle){
		this(angle, false);
	}
	
	public HasReachedAngle(double angle, boolean isRelative){
		this.isRelative = isRelative;
		if(isRelative){
			this.deltaAngle = angle;
		}else{
			this.targetAngle = angle;
		}

	}
	
	@Override
	public void initialize() {
		startAngle = Robot.navigation.getYaw();
		if(isRelative){
			targetAngle = limit(startAngle + deltaAngle);
		}else{
			deltaAngle = degreesToGo();
		}
	}
	
	@Override
	public boolean get() {
		if(deltaAngle > 0){
			return degreesToGo() < 0;
		}else{
			return degreesToGo() > 0;
		}
	}

	public double degreesTraveled(){
		return limit(Robot.navigation.getYaw() - startAngle);
	}
	
	public double degreesToGo(){
		return limit(targetAngle - Robot.navigation.getYaw());
	}
	
	public double limit(double a){
		return VortxMath.continuousLimit(a, -180, 180);
	}
	
	public void setTargetAngle(double angle){
		targetAngle = limit(angle);
	}
	
	public boolean isRelative(){
		return isRelative;
	}
	
	@Override
	public String getHaltMessage() {
		return "Reached an Angle";
	}
	
	
}
