package frc.robot.commands.drive.profiling;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
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
				return Robot.limelight.getTx() * Constants.LimeLight.STEER_K;
			}
    	}, new Func() {
            @Override
            public double getValue() {
                double drivePower;
                if (Robot.limelight.getTv()==1.0) {
                    drivePower  = Robot.limelight.getDistance();
                } else if (Robot.arduino.getDistance()< 45) {
                    drivePower = Robot.arduino.getDistance();
                } else {
                    System.out.println("There is no distance info");
                    drivePower = 0;
                }

                drivePower = (drivePower-Constants.LimeLight.DESIRED_TARGET_DISTANCE) * Constants.LimeLight.DRIVE_K;

                if (drivePower>Constants.LimeLight.MAX_DRIVE) {
                    return Constants.LimeLight.MAX_DRIVE;
                } else {
                    return drivePower;
                } 
            }
        });
    }

	public DriveToTargetP(Func turning, Func driving) {
    	requires(Robot.drive);
		requires(Robot.navigation);
        requires(Robot.limelight);
        requires(Robot.arduino);

        angleCommand = turning;
        driveCommand = driving;
    }
    

	

    // Called just before this Command runs the first time
    protected void initialize() {
 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   

        double move = driveCommand.getValue();
        double turn = angleCommand.getValue();

        SmartDashboard.putNumber("move", move);
        SmartDashboard.putNumber("turn", turn);
        Robot.drive.arcadeDrive(move, turn);
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
		//return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}