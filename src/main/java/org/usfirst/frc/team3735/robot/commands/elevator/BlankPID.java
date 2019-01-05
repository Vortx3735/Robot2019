package org.usfirst.frc.team3735.robot.commands.elevator;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class BlankPID implements PIDSource, PIDOutput {

	public BlankPID() {
		
	}
	@Override
	public void pidWrite(double output) {
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return 0;
	}

}
