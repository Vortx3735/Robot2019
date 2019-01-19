package frc.robot.util;

import frc.robot.util.settings.Func;

public class LogisticController{
	
	double initialPosition;
	double finalPosition;
	
	double dy;
	double k;

	Func startingPower;
	Func maxPower;	
	
	private double constantForce = 0;
	

	
	public LogisticController(Func maxPower, Func startingPower) {
		this.maxPower = maxPower;
		this.startingPower = startingPower;
	}
	
	
	
	
	public void initProfile(double startingPos, double endPos) {
		initialPosition = startingPos;
		finalPosition = endPos;
    	dy = finalPosition - initialPosition;
    	k = Math.abs(4 * maxPower.getValue() / (dy));
	}
	
	public void initProfileStretched(double startingPos, double endPos) {
		initProfile(endPos - (endPos-startingPos) * 1.1, endPos);
	}
	
	public void setConstantForce(double d) {
		constantForce = d;
	}
	
	public double getOutput(double currentPos) {
		double calc = k * (currentPos - initialPosition) * (1- (currentPos / (dy)));
    	if(Math.abs(calc) < startingPower.getValue()) {
    		calc = Math.copySign(startingPower.getValue(), dy);
    	}
    	return calc;
	}
	

	
	
	
	
	

}
