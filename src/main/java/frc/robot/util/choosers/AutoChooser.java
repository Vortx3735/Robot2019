package frc.robot.util.choosers;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	
    private Command selected;
    
	public AutoChooser() {
        
		addDefault("Do Nothing", new DoNothing());
	}
	

	@Override
	public Command getSelected() {
		Command s = super.getSelected();
		if(s == null) {
			System.out.println("Null Auto Detected...");
			return new DoNothing();
		}else {
			return s;
		}
	}
	
	public void startSelected() {
		selected = getSelected();
		selected.start();
	}
	
	public void cancel() {
		if(selected != null) {
			selected.cancel();
		}else {
			System.out.println("The autochooser selected was null... never started");
		}
	}
	
	

	

}


