package frc.robot.util.cmds;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;

public class ComAssist {
	
	public ArrayList<Subsystem> requirements = new ArrayList<Subsystem>();

	public void initialize(){
		
	}
	public void execute(){
		
	}
	public void end(){
		
	}
	
	public void requires(Subsystem s){
		requirements.add(s);
	}
}
