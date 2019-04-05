package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.settings.Func;

/**
 *
 */
public class ElevatorSetPos extends Command {

    Func inches;

    public ElevatorSetPos(Func inches) {
        this.inches = inches;
        requires(Robot.elevator);
        consPower = new ElevatorConsPower();
    }

    public ElevatorSetPos() {
        inches = new Func() {
            @Override
            public double getValue() {
                return SmartDashboard.getNumber("ElevatorHeight", 0.0);
            }
        };
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.controller.setSetpoint(inches.getValue()); //adding one compensates for fall
    	Robot.elevator.controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.controller.updateI();
//    	Robot.elevator.setElevatorPosition(inches.getValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.controller.onTarget();
    }

    Command consPower;
    // Called once after isFinished returns true
    protected void end() {
        Robot.elevator.controller.disable();
        System.out.println("End was called");
        consPower.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
