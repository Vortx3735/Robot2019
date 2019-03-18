package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;
import frc.robot.util.calc.JerkLimiter;
import frc.robot.util.calc.Range;
import frc.robot.util.settings.Setting;
//import frc.robot.util.smartboard.SmartBoard;

/**
 *
 */
public class DDxDrive extends Command {
	
	private JerkLimiter move;
	private JerkLimiter turn;
	
	private double moveMotor, turnMotor;
//	private double maxA = 2;
//	private double maxJ = 1;
	
	private Setting maxA = new Setting("Max Acc", 8, true){
		@Override
		public double getValue(){
			return super.getValue() / 50.0;
		}
	};
	

	
	private Setting maxJ = new Setting("Max Jerk", 100, false){
		@Override
		public double getValue(){
			return super.getValue() / 50.0;
		}
	};
	
	private Setting maxAt = new Setting("Max Acc Turn", 5, true){
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
    

    // Called just before this Command runs the first time
    protected void initialize() {
		//move.reset(Robot.drive.getCurrentPercent());]]\\
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
