package frc.robot;

import frc.robot.commands.auto.MeterStraight;
import edu.wpi.first.wpilibj.command.Command;

public class Autonomous {

	private Command firstCommand;

	public Autonomous() {
		firstCommand = new MeterStraight();
	}

	public void startCommand() {
		firstCommand.start();
	}

	public void cancel() {
	
	}

}
