package org.usfirst.frc.team3735.robot.commands.drive;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.util.calc.JerkLimiter;
import org.usfirst.frc.team3735.robot.util.calc.Range;
import org.usfirst.frc.team3735.robot.util.settings.Func;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DDxDrive extends Command {
	
	private JerkLimiter move;
	private JerkLimiter turn;
	
	private double moveMotor, turnMotor;
//	private double maxA = 2;
//	private double maxJ = 1;
	
	private Setting maxA = new Setting("Max Acc", 10){
		@Override
		public double getValue(){
			return super.getValue() / 50.0;
		}
	};
	
	//create this function and replace maxA with it for limited driving
	private Func maxAEle = new Func() {
		@Override
		public double getValue() {
			double h = Robot.elevator.getPosition();
			return .2;
		}
	};
	
	private Setting maxJ = new Setting("Max Jerk", 100, false){
		@Override
		public double getValue(){
			return super.getValue() / 50.0;
		}
	};
	
	private Setting maxAt = new Setting("Max Acc Turn", 10){
		@Override
		public double getValue(){
			return super.getValue() / 50.0;
		}
	};
	
	private Setting maxJt = new Setting("Max Jerk Turn", 100, false){
		@Override
		public double getValue(){
			return super.getValue() / 50.0;
		}
	};
	
	
    public DDxDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    	move = new JerkLimiter(0, new Range(maxA), new Range(maxJ));
    	turn = new JerkLimiter(0, new Range(maxAt), new Range(maxJt));

    }
    
    public void setIsLimiting(boolean b) {
    	if(b) {
        	move = new JerkLimiter(0, new Range(maxAEle), new Range(maxJ));
    	}else {
        	move = new JerkLimiter(0, new Range(maxA), new Range(maxJ));
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//move.reset(Robot.drive.getCurrentPercent());
    	move.reset();
    	turn.reset();
    	Robot.drive.setupDriveForSpeedControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	moveMotor = Robot.oi.getDriveMove();
    	turnMotor = Robot.oi.getDriveTurn();
    	
		moveMotor = moveMotor * Math.pow(Math.abs(moveMotor), Drive.moveExponent.getValue() - 1);
		turnMotor = turnMotor * Math.pow(Math.abs(turnMotor), Drive.turnExponent.getValue() - 1);
		
    	Robot.drive.normalDrive(
    			move.feed(Robot.oi.getDriveMove()) * Drive.scaledMaxMove.getValue(), 
    			turn.feed(Robot.oi.getDriveTurn()) * Drive.scaledMaxTurn.getValue()
		);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
