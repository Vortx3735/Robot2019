package frc.robot.commands.drive.movedistance;


import frc.robot.Robot;
import frc.robot.util.cmds.VortxCommand;

/**
 *
 */
public class DriveExp extends VortxCommand {

	private double moveSetValue;
	private double turnSetValue;
		
	private double moveMotor;
	private double turnMotor;
	
	private double moveMotorPrev;
	private double turnMotorPrev;
	
	private double moveReactivity = .2;
	private double turnReactivity = .4;
    
    public DriveExp(double move, double turn){
    	moveSetValue = move;
    	turnSetValue = turn;
    	requires(Robot.drive);
    }
    
    public DriveExp(double move, double turn, double mr, double tr){
    	moveSetValue = move;
    	turnSetValue = turn;
    	moveReactivity = mr;
    	turnReactivity = tr;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	Robot.drive.setupDriveForSpeedControl();
		moveMotor = moveMotorPrev = Robot.drive.getPercentOutput();
		turnMotor = turnMotorPrev = 0;

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	super.execute();	
		
		moveMotor = (moveSetValue-moveMotorPrev)*moveReactivity + moveMotorPrev;
		turnMotor = (turnSetValue-turnMotorPrev)*turnReactivity + turnMotorPrev;

		moveMotorPrev = moveMotor;
		turnMotorPrev = turnMotor;
		
					
		moveMotor = moveMotor * Math.pow(Math.abs(moveMotor), 1);
		turnMotor = turnMotor * Math.pow(Math.abs(turnMotor), 1);
		
		Robot.drive.normalDrive(moveMotor, turnMotor);		
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return super.isFinished();
    }

    // Called once after isFinished returns true
    public void end() {
    	Robot.drive.normalDrive(0, 0);
    	super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    	end();
    	super.interrupted();
    }

}
