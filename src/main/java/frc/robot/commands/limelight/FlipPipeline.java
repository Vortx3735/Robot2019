package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FlipPipeline extends Command {

    public FlipPipeline() {

        requires(Robot.limelight);

    }
    
    @Override
    protected void initialize() {
        System.out.println("Flip pipeline called");
        boolean set = Robot.limelight.get3d();
        Robot.limelight.set3D(!set);
    }

	@Override
	protected boolean isFinished() {
		return false;
	}

}