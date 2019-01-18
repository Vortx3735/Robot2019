package org.usfirst.frc.team3735.robot.util.choosers;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DoNothing extends InstantCommand {

    public DoNothing() {
    	
    }

	@Override
	protected void initialize() {
		System.out.println("Running Do Nothing Auto...");
	}
    
    
}
