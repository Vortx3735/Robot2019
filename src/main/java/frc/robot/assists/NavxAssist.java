package frc.robot.assists;

import frc.robot.Robot;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.cmds.ComAssist;
import frc.robot.util.profiling.Location;
import frc.robot.util.settings.Func;

public class NavxAssist extends ComAssist{
	
	Func angle;
	boolean reversed = false;
	Location target;
	public NavxAssist(Func yaw){
		angle = yaw;
	}
	
	public NavxAssist(double yaw) {
		this(Func.getFunc(yaw));
	}
	
	public NavxAssist(){
		
	}
	
	public NavxAssist(Location loc, boolean reversed) {
		this.target = loc;
		this.reversed = reversed;
	}
	
	
	
	@Override
	public void initialize() {
		if(angle == null){
			Robot.navigation.getController().setSetpoint(Robot.navigation.getYaw());
		}else{
			Robot.navigation.getController().setSetpoint(angle.getValue());
		}
	}

	@Override
	public void execute() {
		if(target != null) {
			if(reversed) {
				Robot.navigation.getController().setSetpoint(VortxMath.navLimit(Robot.navigation.getYawToLocation(target) + 180));

			}else {
				Robot.navigation.getController().setSetpoint(Robot.navigation.getYawToLocation(target));

			}
		}
		double error = Robot.navigation.getController().getError();
		Robot.drive.setNavxAssist(error);
		
	}
	
}
