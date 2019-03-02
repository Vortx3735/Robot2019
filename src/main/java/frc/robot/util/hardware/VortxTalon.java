package frc.robot.util.hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.settings.PIDSetting;

public class VortxTalon extends WPI_TalonSRX{
	
	private PIDSetting setting;
	private String name;
	private int timer = 0;
		
	//We update each synchronisly, so that we can only multiply, which is faster
	double ticksPerInch = 1;
	double inchesPerTick = 1;
	
	VortxTalon[] followers;
	
	public VortxTalon(int id){
		this(id, "Talon " + (int)Math.abs(id));
	}
	
	public VortxTalon(int id, String name){
		super((int)Math.abs(id));
		this.name = name;
		this.setInverted((id < 0) ? true : false);
	}
	
	public VortxTalon(int[] ids, String name){
		this(ids[0], name);
		followers = new VortxTalon[ids.length - 1];
		for(int i = 0; i < followers.length; i++) {
			followers[i] = new VortxTalon(ids[i+1]);
			followers[i].follow(this);
		}
	}
	
	@Override
	public void setNeutralMode(NeutralMode mode) {
		super.setNeutralMode(mode);
		if(followers != null) {
			for(VortxTalon t : followers) {
				t.setNeutralMode(mode);
			}
		}
	}
	
	public void putOnDash(){
		setting.sendToDash(name + " PID");
	}

	public void setPID(double kp, double ki, double kd){
		this.config_kP(0, kp * inchesPerTick , 0);
		this.config_kI(0, ki * inchesPerTick, 0);
		this.config_kD(0, kd * inchesPerTick, 0);
		setting.setPID(kp, ki, kd);
	}
	
	public void setPIDF(double kp, double ki, double kd, double kf) {
		this.config_kP(0, kp * inchesPerTick , 0);
		this.config_kI(0, ki * inchesPerTick, 0);
		this.config_kD(0, kd * inchesPerTick, 0);
		this.config_kF(0, kf * inchesPerTick, 0);
		setting.setPID(kp, ki, kd, kf);
	}
	
	public void setPIDSetting(PIDSetting setting){
		this.setting = setting;
		updatePID();
	}
	
	public void updatePID() {
		this.config_kP(0, setting.getP() * inchesPerTick, 0);
		this.config_kI(0, setting.getI() * inchesPerTick, 0);
		this.config_kD(0, setting.getD() * inchesPerTick, 0);
		this.config_kF(0, setting.getF() * inchesPerTick, 0);
		this.config_IntegralZone(0, (int)(setting.getiZone() * ticksPerInch), 0);
		this.configClosedloopRamp(setting.getRampRate(), 0);
	}
	
	public void setFMaxV(double maxv) {
		double nat = maxv * ticksPerInch;
		double f = 1023/nat;
		this.config_kF(0, f, 0);
		setting.setF(f);
	}

	
	public void setTicksPerInch(double ticks){
		this.ticksPerInch = ticks;
		this.inchesPerTick = 1.0/ticks;
	}
	
	public void setInchesPerTick(double inches) {
		this.inchesPerTick = inches;
		this.ticksPerInch = 1.0/inches;
	}
	
	public void initSensor(FeedbackDevice device, boolean reversed){
		this.configSelectedFeedbackSensor(device, 0, 0);
		this.setSelectedSensorPosition(0, 0, 0);
		this.setSensorPhase(reversed);
		this.configNominalOutputForward(0, 0);
		this.configNominalOutputReverse(0, 0);
		this.configPeakOutputForward(1, 0);
		this.configPeakOutputReverse(-1, 0);
//		this.configClosedLoopPeakOutput(0, .7, 0);

	}
	
	public void resetPosition(){
		this.setSelectedSensorPosition(0, 0, 0);
	}
	
	@Override
	public void set(ControlMode mode, double value){

		if(mode == ControlMode.Position || mode == ControlMode.Velocity){
			timer++;
			if(timer % 25 == 0) {
				updatePID();
			}
			value *= ticksPerInch;
		}
		super.set(mode, value);
	}
	
	public void setWithPID(double value, PIDSetting setting){
		setPIDSetting(setting);
		super.set(ControlMode.Position, value * ticksPerInch);
	}
	
	
	public PIDSetting getPIDSetting(){
		return setting;
	}
	
	public double getPosition() {
		return super.getSelectedSensorPosition(0) * inchesPerTick; //return inches
	}
	
	//@return: speed in in/m
	public double getSpeed() {
		return super.getSelectedSensorVelocity(0) * inchesPerTick; //returns
	}
	
	public void log() {
		SmartDashboard.putNumber((name + " Inches"), this.getSelectedSensorPosition(0) * inchesPerTick);

	}
	
	public void debugLog() {
		SmartDashboard.putNumber(name + " P Output", this.getMotorOutputPercent());
		SmartDashboard.putNumber(name + " S Pos", this.getSelectedSensorPosition());
		SmartDashboard.putNumber(name + "Current", this.getOutputCurrent());
		SmartDashboard.putNumber(name + "Voltage", this.getMotorOutputVoltage());
	}
	
}
