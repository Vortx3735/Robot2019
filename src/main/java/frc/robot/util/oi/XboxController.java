package frc.robot.util.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * A standard interface for interacting with a controller that is programmed as shown
 */
public class XboxController extends Joystick{

	public Button a,b,x,y,lb,rb,back,start,ls,rs,lt,rt;
	public Button pov0,pov45,pov90,pov135,pov180,pov225,pov270,pov315;
	
	public XboxController(int p){
		super(p);
		initButtons();
	}

	public void initButtons() {
		a = new JoystickButton(this, 1);
		b = new JoystickButton(this, 2);
		x = new JoystickButton(this, 3);
		y = new JoystickButton(this, 4);
		lb = new JoystickButton(this, 5);
		rb = new JoystickButton(this, 6);
		back = new JoystickButton(this, 7);
		start = new JoystickButton(this, 8);
		ls = new JoystickButton(this, 9);
		rs = new JoystickButton(this, 10);
		lt = new JoystickTriggerButton(this, false, .3);
		rt = new JoystickTriggerButton(this, true, .3);

		pov0 = new JoystickPOVButton(this, 0);
		pov45 = new JoystickPOVButton(this, 45);
		pov90 = new JoystickPOVButton(this, 90);
		pov135 = new JoystickPOVButton(this, 135);
		pov180 = new JoystickPOVButton(this, 180);
		pov225 = new JoystickPOVButton(this, 225);
		pov270 = new JoystickPOVButton(this, 270);
		pov315 = new JoystickPOVButton(this, 315);
	}

	// Getting values from left joystick
	public double getLeftX() {
		return getX();
	}
	public double getLeftY() {
		return getY() * -1;
	}
	public double getLeftMagnitude() {
		return Math.hypot(getLeftX(), getLeftY());
	}
	public double getLeftAngle() {
		return Math.toDegrees(Math.atan2(getLeftX(), getLeftY()));
	}
	
	// Getting values from right joystick
	public double getRightX() {
		return getRawAxis(4);
	}
	public double getRightY() {
		return getRawAxis(5) * -1;
	}
	public double getRightMagnitude() {
		return Math.hypot(getRightX(), getRightY());
	}
	public double getRightAngle() {
		return Math.toDegrees(Math.atan2(getRightX(), getRightY()));
	}

	// Getting values from left trigger
	public double getLeftTrigger() {
		return getZ();
	}

	// Getting values from right trigger
	public double getRightTrigger() {
		return getThrottle();
	}
	
	// Setting the rumble of the controller
	public void setLRRumble(double left, double right){
		super.setRumble(RumbleType.kLeftRumble, left);
		super.setRumble(RumbleType.kRightRumble, right);
	}
	
	public void applyControls(VorTXControllerMap controlMap)
	{
		initButtons();

		// A button
		a.whenPressed(controlMap.a._whenPressedCommand);
		a.whileHeld(controlMap.a._whileHeldCommand);
		a.whenReleased(controlMap.a._whenReleasedCommand);

		// B button
		b.whenPressed(controlMap.b._whenPressedCommand);
		b.whileHeld(controlMap.b._whileHeldCommand);
		b.whenReleased(controlMap.b._whenReleasedCommand);

		// X button
		x.whenPressed(controlMap.x._whenPressedCommand);
		x.whileHeld(controlMap.x._whileHeldCommand);
		x.whenReleased(controlMap.x._whenReleasedCommand);

		// Y button
		y.whenPressed(controlMap.y._whenPressedCommand);
		y.whileHeld(controlMap.y._whileHeldCommand);
		y.whenReleased(controlMap.y._whenReleasedCommand);

		// Start button
		start.whenPressed(controlMap.start._whenPressedCommand);
		start.whileHeld(controlMap.start._whileHeldCommand);
		start.whenReleased(controlMap.start._whenReleasedCommand);

		// Back button
		back.whenPressed(controlMap.back._whenPressedCommand);
		back.whileHeld(controlMap.back._whileHeldCommand);
		back.whenReleased(controlMap.back._whenReleasedCommand);	

		// LB Button
		lb.whenPressed(controlMap.lb._whenPressedCommand);
		lb.whileHeld(controlMap.lb._whileHeldCommand);
		lb.whenReleased(controlMap.lb._whenReleasedCommand);

		// RB Button
		rb.whenPressed(controlMap.rb._whenPressedCommand);
		rb.whileHeld(controlMap.rb._whileHeldCommand);
		rb.whenReleased(controlMap.rb._whenReleasedCommand);

		// Pov 0 button
		pov0.whenPressed(controlMap.pov0._whenPressedCommand);
		pov0.whileHeld(controlMap.pov0._whileHeldCommand);
		pov0.whenReleased(controlMap.pov0._whenReleasedCommand);

		// Pov 90 button
		pov90.whenPressed(controlMap.pov90._whenPressedCommand);
		pov90.whileHeld(controlMap.pov90._whileHeldCommand);
		pov90.whenReleased(controlMap.pov90._whenReleasedCommand);

		// Pov 180 button
		pov180.whenPressed(controlMap.pov180._whenPressedCommand);
		pov180.whileHeld(controlMap.pov180._whileHeldCommand);
		pov180.whenReleased(controlMap.pov180._whenReleasedCommand);

		// Pov 270 button
		pov270.whenPressed(controlMap.pov270._whenPressedCommand);
		pov270.whileHeld(controlMap.pov270._whileHeldCommand);
		pov270.whenReleased(controlMap.pov270._whenReleasedCommand);


	}
	
}
