package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SwitchPipeline extends InstantCommand {

    double d;

    public SwitchPipeline(double d) {
        this.d=d;
    }

    @Override
    protected void initialize() {
        Robot.limelight.setPipeline(d);
    }

}