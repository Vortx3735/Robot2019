package frc.robot.commands.ballarms;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.settings.Func;

/**
 *
 */
public class BallArmsSetPos extends Command {

    int inches;
    Command consPower;


    public BallArmsSetPos(int inches, double power) {
        this.inches = inches;
        consPower = new BallArmsConsPower(power);
        requires(Robot.ballArms);
    }

     

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ballArms.controller.setSetpoint(inches); //adding one compensates for fall
    	Robot.ballArms.controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballArms.controller.updateI();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ballArms.controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.ballArms.controller.disable();
        consPower.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
