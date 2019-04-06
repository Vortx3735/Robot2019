package frc.robot.commands.drive.profiling;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.LimeLight;
import frc.robot.Robot;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.settings.Func;

/**
 *
 */
public class DriveToTargetP extends Command {
	
	// private double finishTime = .3;
    // private double timeOnTarget = 0;
    


    Func angleCommand;
    Func driveCommand;
    int count;
	
	public DriveToTargetP() {
    	this(new Func(){
			@Override
			public double getValue() {
                return VortxMath.limit(
                    (Robot.limelight.getTx()-SmartDashboard.getNumber("Ang offset", LimeLight.ANGLE_OFFSET)) * 
                    SmartDashboard.getNumber("Turn cof", LimeLight.STEER_K),
                    LimeLight.MIN_TURN, LimeLight.MAX_TURN);
			}
    	}, new Func() {
            @Override
            public double getValue() {
                return Math.min(((Robot.arduino.getDistance()-SmartDashboard.getNumber("Dist offset", LimeLight.DISTANCE_OFFSET)) * 
                SmartDashboard.getNumber("Drive cof", LimeLight.STEER_K)),
                 LimeLight.MAX_DRIVE); 
            }
        });
        
        SmartDashboard.putNumber("Drive cof", LimeLight.DRIVE_K);
        SmartDashboard.putNumber("Turn cof", LimeLight.STEER_K);
        SmartDashboard.putNumber("Ang offset", LimeLight.ANGLE_OFFSET);
        SmartDashboard.putNumber("Dist offset", LimeLight.DISTANCE_OFFSET);
    }

	public DriveToTargetP(Func turning, Func driving) {
        requires(Robot.limelight);
        requires(Robot.arduino);

        angleCommand = turning;
        driveCommand = driving;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.limelight.setPipeline(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   

        double move = driveCommand.getValue();
        double turn = angleCommand.getValue();

        SmartDashboard.putNumber("move", move);
        SmartDashboard.putNumber("turn", turn);
        //add the values to the drive class
        Robot.drive.arcadeDrive(move, turn);
        // Robot.drive.setMoveVisionAssist(move);
        // Robot.drive.setTurnVisionAssist(turn);
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
		//return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
        // Robot.drive.setMoveVisionAssist(0);
        // Robot.drive.setTurnVisionAssist(0);
        Robot.limelight.setPipeline(1.0); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}