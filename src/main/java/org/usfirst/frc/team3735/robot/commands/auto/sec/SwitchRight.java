package org.usfirst.frc.team3735.robot.commands.auto.sec;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosRaw;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.GrabWallCube;
import org.usfirst.frc.team3735.robot.commands.sequences.SecondCubeSwitchGrab;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchRight extends CommandGroup {

    public SwitchRight(boolean complex) {
    	
    	addSequential(new SecondCubeSwitchGrab());
    	addSequential(new TurnTo(Waypoints.Pieces.switchRight, true), 1);
    	addSequential(new AutoSwitchLineup(true));
    	
    }
}
