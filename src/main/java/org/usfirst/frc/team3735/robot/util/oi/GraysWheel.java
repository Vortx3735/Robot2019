package org.usfirst.frc.team3735.robot.util.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * A standard interface for interacting with a controller that is programmed as shown
 */
public class GraysWheel extends Joystick{
	//public Button mid, left, right, trig;
	public Button pov0,pov45,pov90,pov135,pov180,pov225,pov270,pov315;
	public Button x, circle, triangle, square;
	public Button start, select, r1, r2, r3, l1, l2, l3;
	Button plus, minus;
	
	public GraysWheel(int p){
		super(p);
		x = new JoystickButton(this, 1);
		circle = new JoystickButton(this, 3);
		triangle = new JoystickButton(this, 4);
		square = new JoystickButton(this, 2);
		
		r1 = new JoystickButton(this, 5);
		r2 = new JoystickButton(this, 7);
		r3 = new JoystickButton(this, 11);
		l1 = new JoystickButton(this, 6);
		l2 = new JoystickButton(this, 8);
		l3 = new JoystickButton(this, 12);
		
		start = new JoystickButton(this, 10);
		select = new JoystickButton(this, 9);

		plus = new JoystickButton(this, 13);
		minus = new JoystickButton(this, 14);
		
		pov0 = new JoystickPOVButton(this, 0);
		pov45 = new JoystickPOVButton(this, 45);
		pov90 = new JoystickPOVButton(this, 90);
		pov135 = new JoystickPOVButton(this, 135);
		pov180 = new JoystickPOVButton(this, 180);
		pov225 = new JoystickPOVButton(this, 225);
		pov270 = new JoystickPOVButton(this, 270);
		pov315 = new JoystickPOVButton(this, 315);
	}

	public double getGYaw() {
		return Math.toDegrees(Math.atan2(getGX(), getGY()));
	}
	
	public double getGX() {
		return getRawAxis(0);
	}
	
	public double getGY() {
		return getRawAxis(1) * -1;
	}
	public double getGMagnitude() {
		return Math.hypot(getGX(), getGY());
	}
	
	@Override
	public double getThrottle() {
		return getRawAxis(2);
	}

	

	
	
	
}
