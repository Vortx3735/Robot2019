package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.LeftHab2PlaceHatch;
import frc.robot.commands.auto.MiddleHatch;
import frc.robot.commands.auto.RightHab2PlaceHatch;
import frc.robot.commands.ballarms.BallArmsConsPower;
import frc.robot.util.choosers.DoNothing;

public class Autonomous {

	private SendableChooser<Command> autoChooser;

	public Autonomous() {
		autoChooser = new SendableChooser<>();
			autoChooser.addOption("Left Hab 2 Hatch", new LeftHab2PlaceHatch());
			autoChooser.addOption("Right Hab 2 Hatch", new RightHab2PlaceHatch());
			autoChooser.addOption("Middle Hab 1 Hatch", new MiddleHatch());
			autoChooser.setDefaultOption("Do Nothing", new DoNothing());
			//autoChooser.setDefaultOption("Hold Arm", new BallArmsConsPower(.15));


		SmartDashboard.putData(autoChooser);

	}

	public void startCommand() {
		autoChooser.getSelected().start();
	}

	public void cancel() {
		autoChooser.getSelected().cancel();
	
	}

}
