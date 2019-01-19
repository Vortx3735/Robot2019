package frc.robot.util.choosers;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class SideChooser extends SendableChooser<Side> {
	
	public SideChooser() {
		addOption("Red", Side.RED);
		addOption("Blue", Side.BLUE);
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


