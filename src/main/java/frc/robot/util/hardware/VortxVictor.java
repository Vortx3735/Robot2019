package frc.robot.util.hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.settings.PIDSetting;

public class VortxVictor extends WPI_VictorSPX{
	
	private PIDSetting setting;
	private String name;
	
	double ticksPerInch = 1;
	
	/**
	 * Copied straight from VortxTalon- talon has most updated PID code, 
	 * @param id
	 */
	public VortxVictor(int id){
		this(id, "Talon " + (int)Math.abs(id));
	}
	
	public VortxVictor(int id, String name){
		super((int)Math.abs(id));
		this.name = name;
		
		if(id < 0) {
			this.setInverted(true);
		}else {
			this.setInverted(false);
		}
	}
	
	public void putOnDash(){
		setting.sendToDash(name + " PID");
	}

	public void setPID(double kp, double ki, double kd){
		this.config_kP(0, kp / ticksPerInch , 0);
		this.config_kI(0, ki / ticksPerInch, 0);
		this.config_kD(0, kd / ticksPerInch, 0);
		setting.setPID(kp, ki, kd);
	}
	
	public void setPIDF(double kp, double ki, double kd, double kf) {
		this.config_kP(0, kp / ticksPerInch , 0);
		this.config_kI(0, ki / ticksPerInch, 0);
		this.config_kD(0, kd / ticksPerInch, 0);
		this.config_kF(0, kf / ticksPerInch, 0);
		setting.setPID(kp, ki, kd, kf);
	}
	
	public void setPIDSetting(PIDSetting setting){
		this.setting = setting;
		updatePID();
	}
	
	public void updatePID() {
		this.config_kP(0, setting.getP() / ticksPerInch, 0);
		this.config_kI(0, setting.getI() / ticksPerInch, 0);
		this.config_kD(0, setting.getD() / ticksPerInch, 0);
		this.config_kF(0, setting.getF() / ticksPerInch, 0);
		this.config_IntegralZone(0, (int)(setting.getiZone()*ticksPerInch), 0);
		this.configClosedloopRamp(setting.getRampRate(), 0);
	}

	
	public void setTicksPerInch(double ticks){
		this.ticksPerInch = ticks;
	}
	
	public void initSensor(FeedbackDevice device, boolean reversed){
		this.configSelectedFeedbackSensor(device, 0, 0);
		this.setSelectedSensorPosition(0, 0, 0);
		this.setSensorPhase(reversed);
		this.configNominalOutputForward(0, 0);
		this.configNominalOutputReverse(0, 0);
		this.configPeakOutputForward(1, 0);
		this.configPeakOutputReverse(-1, 0);
	}
	
	public void resetPosition(){
		this.setSelectedSensorPosition(0, 0, 0);
	}
	
	@Override
	public void set(ControlMode mode, double value){
		if(mode == ControlMode.Position){
			value *= ticksPerInch;
		}
		super.set(mode, value);
	}
	
	public void setWithPID(double value, PIDSetting setting){
		setPIDSetting(setting);
		set(ControlMode.Position, value);
	}
	
	public PIDSetting getPIDSetting(){
		return setting;
	}
	
	public double getPosition() {
		return super.getSelectedSensorPosition(0) / ticksPerInch;
	}
	
	public void log() {
		SmartDashboard.putNumber((name + " Inches"), this.getSelectedSensorPosition(0)/ticksPerInch);

	}
	
	public void debugLog() {
		SmartDashboard.putNumber(name + " P Output", this.getMotorOutputPercent());
//		SmartDashboard.putNumber(name + " S Pos", this.getSelectedSensorPosition(0));
	}
	
	public double getPower() {
		return this.getOutputCurrent() * this.getMotorOutputVoltage();
	}

	

	
}
