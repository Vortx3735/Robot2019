package frc.robot.triggers;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.util.cmds.ComTrigger;

public class TimeOut extends ComTrigger {
	
	private Double timeout;
	private Command parent;

	public TimeOut(double timeout, Command p) {
		this.timeout = timeout;
		parent = p;
	}


	@Override
	public boolean get() {
		return parent.timeSinceInitialized() > timeout;
	}

	@Override
	public String getHaltMessage() {
		return "timed out by " + timeout + "seconds";
	}
	
	
	
	
}
