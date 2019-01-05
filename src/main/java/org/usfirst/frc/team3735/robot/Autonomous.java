package org.usfirst.frc.team3735.robot;

import static org.junit.Assume.assumeNoException;

import org.usfirst.frc.team3735.robot.commands.auto.*;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.util.choosers.DoNothing;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.profiling.Position;
import org.usfirst.frc.team3735.robot.util.settings.BooleanSetting;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

	private SendableChooser<Command> autoChooser;
	private SendableChooser<Pos> posChooser;

	public Autonomous() {
		autoChooser = new SendableChooser<>();
		posChooser = new SendableChooser<>();

		posChooser.addOption("Unknown", Pos.UNKNOWN);
		posChooser.addOption("Left", Pos.LEFT);
		posChooser.addOption("Mid", Pos.MID);
		posChooser.addOption("Right", Pos.RIGHT);

		autoChooser.addOption("Do Nothing", new DoNothing());
		autoChooser.addOption("Unknown Straight", new UnknownStraight());

	}

	public void startCommand() {
		if (autoChooser.getSelected() != null) {
			autoChooser.getSelected().start();
		}
	}



	public void cancel() {
		if (autoChooser.getSelected() != null) {
			autoChooser.getSelected().cancel();
		}
	}

	enum Pos{
		LEFT,
		MID,
		RIGHT,
		UNKNOWN;
	}


	public Position getStartingPosition() {
		switch (posChooser.getSelected()) {
			case LEFT:
				return new Position(-Dms.Field.HALFWALLWIDTH + Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 180);
			case MID:
			case UNKNOWN:
				return new Position(-12 + Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 180);
			case RIGHT:
				return new Position(Dms.Field.HALFWALLWIDTH - Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 180);

			default:
				return new Position(0, Dms.Bot.HALFLENGTH, 180);
		}
	}

}
