package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VortxTalon motor1;
	
	Setting initialSpeed;
	Setting tensionSpeed;
	
	public Climber(){
		motor1 = new VortxTalon(RobotMap.Climber.motor);
		motor1.setNeutralMode(NeutralMode.Coast);
				
	}
	
	public void setMotorCurrent(double speed){
		motor1.set(speed);
	}
	

	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

