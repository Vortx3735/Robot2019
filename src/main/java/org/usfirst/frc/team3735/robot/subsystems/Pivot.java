package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeIntakeJoystickMove;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.PIDCtrl;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pivot extends Subsystem implements PIDSource, PIDOutput{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VortxTalon pivot;
	public PIDCtrl controller;
	
	private Setting cons = new Setting("Angler Cons Power", .12);
	public Setting ticksPerDegree = new Setting("Pivot Ticks Per Degree", -2.8444444);//- For practice
			
	public Pivot() {
		pivot = new VortxTalon(RobotMap.CubeIntake.anglerMotor, "Angler");
		pivot.setNeutralMode(NeutralMode.Brake);
		
		controller = new PIDCtrl(.017,.002,0.01,0,this,this, 5);
		controller.setAbsoluteTolerance(2);
		SmartDashboard.putData("Cube Angler PID", controller);
		 
		pivot.setTicksPerInch(ticksPerDegree.getValue());//Will be ticksPerDegree
		pivot.initSensor(FeedbackDevice.QuadEncoder, false);
		resetEncoderPositions();
		
		pivot.configContinuousCurrentLimit(0, 0);
		pivot.configPeakCurrentLimit(2, 0);
		pivot.configPeakCurrentDuration(2000, 0);
		pivot.enableCurrentLimit(true);
	}
		
	public double getPosition() {
		return pivot.getPosition();
	}
	
	public void resetEncoderPositions() {
		pivot.resetPosition();
	}
	
	
    public void initDefaultCommand() {
        setDefaultCommand(new CubeIntakeJoystickMove());
    }

	public void setPOutput(double anglerMove) {
		pivot.set(anglerMove);	
	}

	@Override
	public void pidWrite(double val) {
		setPOutput(VortxMath.limit(val, -.5, .5));
	}
	

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getPosition();
	}
	

	public void log() {
//		SmartDashboard.putNumber("Cube Angler Angle", getPosition());
		SmartDashboard.putNumber("Cube Angler Angle", getPosition());
//		SmartDashboard.putNumber("Pivot Percent ", value)
		pivot.debugLog();
		SmartDashboard.putNumber("Cube Angler Current", pivot.getOutputCurrent());
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}

}

