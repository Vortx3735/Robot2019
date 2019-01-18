package org.usfirst.frc.team3735.robot.commands.drive.movedistance;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	this is a shortcut class for simple moving a distance
 */
public class Move extends MoveDDx {

	static double defaultAcc = .04;
	static double defaultMaxV = .6;
    public Move(double dist) {
    	this(dist, defaultMaxV, defaultAcc);
    }
    
    public Move(double dist, double spd, double acc) {
//    	super(Math.signum(dist) * spd, spd, acc);
    	super(dist, Math.signum(dist) * spd, acc);
    	addA(new NavxAssist());
//    	addT(new HasMoved(dist));
    }
}
