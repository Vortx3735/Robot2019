package org.usfirst.frc.team3735.robot.util.calc;

public class Average {
	
	public double sum;
	public double n;
	
	public Average() {
		
	}
	
	public void add(double num) {
		sum += num;
		n++;
	}
	
	public double getAverage() {
		return sum / n;
	}
	
	
}
