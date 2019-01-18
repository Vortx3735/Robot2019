package frc.robot.commands.drive.movedistance;

import frc.robot.assists.NavxAssist;

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
