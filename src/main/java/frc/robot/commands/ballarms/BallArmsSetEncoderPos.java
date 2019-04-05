package frc.robot.commands.ballarms;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class BallArmsSetEncoderPos extends InstantCommand {

    int pos;

    public BallArmsSetEncoderPos(int pos) {
        this.setRunWhenDisabled(true);
        this.pos = pos;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.ballArms.setEncoderPos(pos);

    }

}