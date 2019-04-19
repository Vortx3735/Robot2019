package frc.robot.commands.ballarms;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.PIDCtrl;
import frc.robot.util.settings.Func;

/**
 *
 */
public class BallArmsSetPos extends Command {

    double pos;
    Command consPower;
    PIDCtrl controller;

    double timeOn;


    public BallArmsSetPos(double pos, double power, boolean up) {
        this.pos = pos;
        if(up) {
            controller = Robot.ballArms.controllerUp;
        } else {
            controller = Robot.ballArms.controllerDown;
        }
        consPower = new BallArmsConsPower(power);
        requires(Robot.ballArms);
    }

     

    // Called just before this Command runs the first time
    protected void initialize() {
    	controller.setSetpoint(pos);
    	controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(controller.onTarget()) {
            timeOn+=.02;
        } else {
            //System.out.println(-1*controller.getError()*controller.getP());
        }
        System.out.println("On target for " + timeOn + " arms move" + Robot.oi.getArmsMove() + "Error " + controller.getError());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeOn>1 || Math.abs(Robot.oi.getArmsMove()) >=.05;
    }

    // Called once after isFinished returns true
    protected void end() {
        controller.disable();
        consPower.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
