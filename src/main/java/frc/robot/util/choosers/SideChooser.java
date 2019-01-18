package org.usfirst.frc.team3735.robot.util.choosers;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SideChooser extends SendableChooser<Side> {
	
	public SideChooser() {
		addDefault("Red", Side.RED);
		addObject("Blue", Side.BLUE);
	}
	

	@Override
	public Side getSelected() {
		Side s = super.getSelected();
		if(s == null) {
			System.out.println("Null Side detected... returning Red");
			return Side.RED;
		}else {
			return s;
		}
	}
	
	

	

}


