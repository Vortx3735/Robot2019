package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.commands.auto.*;
import org.usfirst.frc.team3735.robot.commands.auto.sec.*;
import org.usfirst.frc.team3735.robot.commands.sequences.GrabWallCube;
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

	private SendableChooser<StartingState> posChooser;
	private SendableChooser<Priority> priority;
	private BooleanSetting complexity;
	// private SendableChooser

	private Command firstCommand = new DoNothing();
	private Command secondCommand = new DoNothing();

	private Command mainCommand = new DoNothing();

	public Autonomous() {

		posChooser = new SendableChooser<>();
		posChooser.addDefault("Unknown", StartingState.UNKNOWN);
		posChooser.addObject("Left", StartingState.LEFT);
		posChooser.addObject("Middle", StartingState.MID);
		posChooser.addObject("Right", StartingState.RIGHT);
		SmartDashboard.putData("Starting Pos", posChooser);

		priority = new SendableChooser<>();
		priority.addDefault("Cross Line", Priority.LINE);
		priority.addObject("Switch", Priority.SWITCH);
		priority.addObject("Scale", Priority.SCALE);
		priority.addObject("Scale if Easy", Priority.SCALEIFEASY);
		priority.addObject("Testing", Priority.TESTING);
		SmartDashboard.putData("Priority", priority);

		complexity = new BooleanSetting("Complex Auto", false);

	}

	public void startCommand() {
		// firstCommand.start();
		if (firstCommand != null && secondCommand != null) {
			// PUT SECOND COMMANAD HERE WHEN READY
			// mainCommand = VortxCommand.asSequence(firstCommand, new
			// DoNothing());
			//firstCommand = new TestStraightLine();
			mainCommand = VortxCommand.asSequence(firstCommand, secondCommand);
			printAuto();
			mainCommand.start();

		}
	}

	public void chooseAutonomous() {

		cancel();

		String s = DriverStation.getInstance().getGameSpecificMessage().toLowerCase().trim().substring(0, 2);

		System.out.println("Specific Message interpreted: " + s);

		if (priority.getSelected() == null || priority.getSelected() == Priority.LINE) {
			firstCommand = new UnknownStraight();
			System.out.println("Doing Unknown Straight B");
			return;
		}else if (priority.getSelected() == Priority.TESTING) {
			System.out.println("Doing Testing");
			firstCommand = new TestStraightLine();
			
			return;
		}
		

		boolean complex = complexity.getValue();

		// if(s.charAt(0) == 'r'){
		// firstCommand = new MuchoSimpleRight(true);
		// }else{
		// firstCommand = new MuchoSimpleRight(false);
		// }

		if (priority.getSelected() == Priority.SWITCH) {
			switch (posChooser.getSelected()) {
			case LEFT:
				if (s.charAt(0) == 'l') {
					firstCommand = new MuchoSimpleLeft(true);
				} else {
					firstCommand = new MuchoSimpleLeft(false);
				}
				break;
			case MID:
				if (s.charAt(0) == 'r') {
					firstCommand = new MuchoSimpleCenter(true);
				} else {
					firstCommand = new MuchoSimpleCenter(false);
				}
				break;
			case UNKNOWN:
				break;
			case RIGHT:
				if (s.charAt(0) == 'r') {
					firstCommand = new MuchoSimpleRight(true);
				} else {
					firstCommand = new MuchoSimpleRight(false);
				}
				break;
			default:
				// return new Position(0, Dms.Bot.HALFLENGTH, 180);
			}
		}else if(priority.getSelected() == Priority.SCALE){
			switch (posChooser.getSelected()) {
			case LEFT:
				if (s.charAt(1) == 'l') {
					firstCommand = new MuchoSimpleScaleLeft(true);
				} else {
					firstCommand = new MuchoSimpleScaleLeft(false);
				}
				break;
			case MID:
				break;
			case UNKNOWN:
				break;
			case RIGHT:
				if (s.charAt(1) == 'r') {
					firstCommand = new MuchoSimpleScaleRight(true);
				} else {
					firstCommand = new MuchoSimpleScaleRight(false);
				}
				break;
			default:
				break;
			}		
		}else if(priority.getSelected() == Priority.SCALEIFEASY){
			switch (posChooser.getSelected()) {
			case LEFT:
				if (s.charAt(1) == 'l') {
					firstCommand = new MuchoSimpleScaleLeft(true);
				} else if(s.charAt(0) == 'l'){
					firstCommand = new MuchoSimpleLeft(true);
				}else {
					firstCommand = new MuchoSimpleScaleLeft(false);
				}
				break;
			case MID:
				break;
			case UNKNOWN:
				break;
			case RIGHT:
				if (s.charAt(1) == 'r') {
					firstCommand = new MuchoSimpleScaleRight(true);
				} else if(s.charAt(0) == 'r'){
					firstCommand = new MuchoSimpleRight(true);
				}else {
					firstCommand = new MuchoSimpleScaleRight(false);
				}
				break;
			default:
				break;
			}
		}
		// big:
		// switch(posChooser.getSelected()) {
		//
		//
		// case LEFT:
		// switch(priority.getSelected()) {
		// case SCALE:
		// switch(s) {
		// case "ll":
		// case "rl":
		// firstCommand = new LeftScaleLeft(complex); break big;
		// case "lr":
		// case "rr":
		//// firstCommand = new LeftScaleRight(complex); break big;
		// firstCommand = new UnknownStraight(); break big;
		// }
		// case SCALEIFEASY:
		// switch(s) {
		// case "ll":
		// case "rl":
		// firstCommand = new LeftScaleLeft(complex); break big;
		// case "lr":
		// firstCommand = new LeftSwitchLeft(complex); break big;
		// case "rr":
		// firstCommand = new UnknownStraight(); break big;
		// }
		// case SWITCH:
		// switch(s) {
		// case "lr":
		// case "ll":
		// firstCommand = new LeftSwitchLeft(complex); break big;
		// case "rr":
		// case "rl":
		// firstCommand = new UnknownStraight(); break big;
		// }
		// }
		//
		//
		//
		//
		//
		// case MID:
		// switch(priority.getSelected()){
		// case SCALE:
		// switch(s) {
		// case "ll":
		// case "rl":
		// firstCommand = new MidScaleLeft(complex); break big;
		// case "rr":
		// case "lr":
		// firstCommand = new MidScaleRight(complex); break big;
		// }
		// case SCALEIFEASY:
		// case SWITCH:
		// switch(s) {
		// case "ll":
		// case "lr":
		// firstCommand = new MidSwitchLeft(complex);//break big;
		// secondCommand = new SwitchLeft(complex); break big;
		// case "rl":
		// case "rr":
		// firstCommand = new MidSwitchRight(complex); //break big;
		// secondCommand = new SwitchRight(complex); break big;
		//
		// }
		// break;
		//
		// }
		//
		//
		//
		//
		//
		// case RIGHT:
		// switch(priority.getSelected()) {
		// case SCALE:
		// switch(s) {
		// case "lr":
		// case "rr":
		// firstCommand = new RightScaleRight(complex); break big;
		// case "ll":
		// case "rl":
		//// firstCommand = new RightScaleLeft(complex); break big;
		// firstCommand = new UnknownStraight(); break big;
		// }
		// case SCALEIFEASY:
		// switch(s) {
		// case "lr":
		// case "rr":
		// firstCommand = new RightScaleRight(complex); break big;
		// case "rl":
		// firstCommand = new RightSwitchRight(complex); break big;
		// case "ll":
		// firstCommand = new UnknownStraight(); break big;
		// }
		// case SWITCH:
		// switch(s) {
		// case "rl":
		// case "rr":
		// firstCommand = new RightSwitchRight(complex); break big;
		// case "ll":
		// case "lr":
		// firstCommand = new UnknownStraight(); break big;
		// }
		// }
		//
		// case UNKNOWN:
		// break;
		//
		// }
	}

	public void printAuto() {
		System.out.println("Auto Logic Selected: " + firstCommand.getName() + " and " + secondCommand.getName());
	}

	public void cancel() {
		if (mainCommand != null) {
			mainCommand.cancel();
		}

	}

	public enum Priority {
		SWITCH, SCALE, SCALEIFEASY, LINE, TESTING
	}

	public static enum StartingState {
		LEFT, MID, RIGHT, UNKNOWN
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
