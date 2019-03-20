package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.drive.movedistance.GetOffHab2;

public class Autonomous {

	private Command firstCommand;

	public Autonomous() {
		firstCommand = new GetOffHab2();
	}

	public void startCommand() {
		firstCommand.start();
	}

	public void cancel() {
	
	}

}
