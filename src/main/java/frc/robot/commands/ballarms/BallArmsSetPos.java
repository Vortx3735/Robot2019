package frc.robot.commands.ballarms;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.settings.Func;

/**
 *
 */
public class BallArmsSetPos extends Command {

    Func inches;
    //    Command consPower;


    public BallArmsSetPos(Func inches) {
        this.inches = inches;
        //consPower = new BallArmsConsPower();
        requires(Robot.elevator);
    }

    public BallArmsSetPos() {
        inches = new Func() {
            @Override
            public double getValue() {
                return SmartDashboard.getNumber("BallArmsPos", 0.0);
            }
        };
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ballArms.controller.setSetpoint(inches.getValue()); //adding one compensates for fall
    	Robot.ballArms.controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballArms.controller.updateI();
//    	Robot.elevator.setElevatorPosition(inches.getValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ballArms.controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.elevator.controller.disable();
 //       consPower.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
