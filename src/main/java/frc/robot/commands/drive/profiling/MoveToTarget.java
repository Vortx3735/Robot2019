package frc.robot.commands.drive.profiling;



import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.settings.Func;

/**
 *
 */
public class MoveToTarget extends Command {
	
	// private double finishTime = .3;
	// private double timeOnTarget = 0;

	Func getAngle;
	int count;
	
	public MoveToTarget() {
    	this(new Func(){
			@Override
			public double getValue() {
				return VortxMath.navLimit(Robot.navigation.getYaw()+Robot.limelight.getTx());
			}
    	});
    }

	public MoveToTarget(Func fun) {
    	requires(Robot.drive);
		requires(Robot.navigation);
		requires(Robot.limelight);
		getAngle = fun;
		count = 0;
	}
	

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.navigation.getController().setSetpoint(getAngle.getValue());
    	Robot.navigation.getController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   
		count++;
		if(count%10==0) {
			//Robot.navigation.getController().disable();
			System.out.println("New setpoint is " + getAngle.getValue());
			Robot.navigation.getController().setSetpoint(getAngle.getValue());	
			//Robot.navigation.getController().enable();
		} 
    	// if(Robot.navigation.getController().onTarget()){
    	// 	timeOnTarget += .02;
    	// }else{
    	// 	timeOnTarget = 0;
    	// }
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
		//return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.navigation.getController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}