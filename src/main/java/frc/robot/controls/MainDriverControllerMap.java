package frc.robot.controls;

import frc.robot.util.oi.*;
import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.winch.*;

public class MainDriverControllerMap extends VorTXControllerMap {
	
    public MainDriverControllerMap() {
		super();
        
        a.whileHeld(new BallIntakeMotorSet(0.75));
		b.whileHeld(new BallIntakeMotorSet(-0.75));
		x.whileHeld(new BallIntakeMotorSet(0.0));

		//Hatch commands
		lb.whenPressed(new HatchSet(true));
	    rb.whenPressed(new HatchSet(false));

		//Winch commands
		pov90.whenPressed(new SetWinchSolenoids(true));
		pov270.whenPressed(new SetWinchSolenoids(false));

		start.whileHeld(new SetWinchSpeed(1.0));
		back.whenPressed(new SetWinchSpeed(0.0));
    }
}