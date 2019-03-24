package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ElevatorFree extends InstantCommand {

    public ElevatorFree() {
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {
        Robot.elevator.setPOutput(0.0);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}