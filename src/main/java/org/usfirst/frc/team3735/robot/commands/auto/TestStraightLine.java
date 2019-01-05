package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveMoveDistancePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestStraightLine extends CommandGroup {

    public TestStraightLine() {
        addSequential(new DriveMoveDistancePID(12));
    }
    
    public void initialize() {
    	System.out.println("starting very simple");
    }
    
    public void end() {
    	System.out.println("ending very simple");
    }
}
