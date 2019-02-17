package frc.robot.subsystems;


//import org.usfirst.frc.team3735.robot.settings.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import Constants;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.VortxSubsystem;


public class LEDS extends VortxSubsystem {
	I2C com;
	
	public static enum Data{
		NOTHING(5),
		BGFLASH(10),
		REDFIRE(20),
		BLUEFIRE(40),
		NORMALFIRE(30);
		
		
		private final byte value;
		
		Data(int i){
			if(i < 128 && i > -129) {
				value = (byte)i;
			}else {
				System.out.println("Error parsing LED Data... " + i + " is not a valid byte value");
				value = 0;
			}
		}
		
		public byte convert() {
			return value;
		}
	}
	
	public LEDS(){
		super("leds","LED");
		com = new I2C(I2C.Port.kOnboard, 5);
		sendData(Data.NOTHING);
	}
	
	//teleop
	public void SendDataAutonomous(){
		sendData(Data.BGFLASH);
	}
	
	//auto
	public void SendDataTeleop(){
		sendData(Data.NORMALFIRE);
	}
	
	

	public void sendData(Data dat) {
		byte[] real = new byte[1];
		byte[] dummy = new byte[0];
		real[0] = dat.convert();
		com.transaction(real, 1, dummy, 0);
		Timer.delay(.03);
	}
		
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

		
}
