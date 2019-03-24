package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.LeftHab2PlaceHatch;
import frc.robot.commands.auto.MiddleHatch;
import frc.robot.commands.auto.RightHab2PlaceHatch;
import frc.robot.commands.drive.ExpDrive;
import frc.robot.commands.drive.profiling.PathFollower;
import frc.robot.util.choosers.DoNothing;
import jaci.pathfinder.Waypoint;

public class Autonomous {

	private Command firstCommand;
	private SendableChooser<Command> autoChooser;


	public Autonomous() {
		autoChooser = new SendableChooser<>();
			autoChooser.addObject("Left Hab 2 Hatch", new LeftHab2PlaceHatch());
			autoChooser.addObject("Right Hab 2 Hatch", new RightHab2PlaceHatch());
			autoChooser.addObject("Middle Hab 1 Hatch", new MiddleHatch());
			autoChooser.addDefault("Do Nothing", new DoNothing());

		SmartDashboard.putData(autoChooser);

	}

	public void startCommand() {
		autoChooser.getSelected().start();
	}

	public void cancel() {
		autoChooser.getSelected().cancel();
	
	}

}
