package org.usfirst.frc.team3735.robot.commands.carriage;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CarriageLower extends InstantCommand {

    public CarriageLower() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//    	requires(Robot.carriage);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.carriage.lower();

    }

    
    
}
