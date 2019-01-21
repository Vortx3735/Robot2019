package frc.robot.assists;

import frc.robot.Robot;
import frc.robot.util.cmds.ComAssist;
import frc.robot.util.settings.Func;

public class JevoisAssist extends ComAssist {

    Func angle;

    public JevoisAssist(Func yaw){
		angle = yaw;
    }
    
    @Override
	public void initialize() {

	}

	@Override
	public void execute() {

		// double error = Robot.navigation.getController().getError();
		//Robot.drive.setNavxAssist(error);
		
	}


}