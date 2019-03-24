package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class EndAll extends Command {

    public EndAll() {
        this.setRunWhenDisabled(true);
        requires(Robot.drive);
        requires(Robot.elevator);
        requires(Robot.ballIntake);
        requires(Robot.hatchIntake);
        requires(Robot.suck);
        requires(Robot.carriage);
        requires(Robot.endgame);
        System.out.println("EndAll created");
    }


    @Override
    protected void execute() {
        System.out.println("Reset everything");
        Robot.elevator.setPOutput(0.0);
        Robot.drive.arcadeDrive(0, 0);
        Robot.ballIntake.setIntake(0);
        Robot.hatchIntake.setSolenoid(Robot.hatchIntake.solenoid.get());
        Robot.suck.setSolenoid(Robot.suck.solenoid.get());
        Robot.carriage.setSolenoid(Robot.carriage.solenoid.get());
        Robot.endgame.setMotorSpeed(0.0);
    }

	@Override
	protected boolean isFinished() {
		return true;
	}

}
