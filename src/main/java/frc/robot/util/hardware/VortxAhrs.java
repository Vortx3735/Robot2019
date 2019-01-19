package frc.robot.util.hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.calc.VortxMath;

public class VortxAhrs extends AHRS{

	private double offset = 0;
	
	public VortxAhrs(Port p){
		super(p);
	}
	
	public void setYaw(double yaw) {
		super.zeroYaw();
		offset = yaw;
	}
	
	@Override
	public float getYaw() {
		return (float)VortxMath.navLimit((double)super.getYaw() + offset);
	}
	

	
	   
    public void displayDebugGyroData(){
    	SmartDashboard.putBoolean(  "IMU_Connected",        super.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    super.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              super.getYaw());
        SmartDashboard.putNumber(   "IMU_Pitch",            super.getPitch());
        SmartDashboard.putNumber(   "IMU_Roll",             super.getRoll());
        
        /* Display tilt-corrected, Magnetometer-based heading (requires             */
        /* magnetometer calibration to be useful)                                   */
        SmartDashboard.putNumber(   "IMU_CompassHeading",   super.getCompassHeading());
        
        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
        SmartDashboard.putNumber(   "IMU_FusedHeading",     super.getFusedHeading());

        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
        /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
        SmartDashboard.putNumber(   "IMU_TotalYaw",         super.getAngle());
        SmartDashboard.putNumber(   "IMU_YawRateDPS",       super.getRate());

        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
        SmartDashboard.putNumber(   "IMU_Accel_X",          super.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU_Accel_Y",          super.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU_IsMoving",         super.isMoving());
        SmartDashboard.putBoolean(  "IMU_IsRotating",       super.isRotating());
        

        /* Display estimates of velocity/displacement.  Note that these values are  */
        /* not expected to be accurate enough for estimating robot position on a    */
        /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
        /* of these errors due to single (velocity) integration and especially      */
        /* double (displacement) integration.                                       */
        
        SmartDashboard.putNumber(   "Velocity_X",           super.getVelocityX());
        SmartDashboard.putNumber(   "Velocity_Y",           super.getVelocityY());
        SmartDashboard.putNumber(   "Displacement_X",       super.getDisplacementX());
        SmartDashboard.putNumber(   "Displacement_Y",       super.getDisplacementY());
        
        SmartDashboard.putNumber("Displacement Total", 
    		Math.sqrt(Math.pow(super.getDisplacementX(),2) + Math.pow(super.getDisplacementY(),2)));
        
        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
        /* NOTE:  These values are not normally necessary, but are made available   */
        /* for advanced users.  Before using super data, please consider whether     */
        /* the processed data (see above) will suit your needs.                     */
        
        SmartDashboard.putNumber(   "RawGyro_X",            super.getRawGyroX());
        SmartDashboard.putNumber(   "RawGyro_Y",            super.getRawGyroY());
        SmartDashboard.putNumber(   "RawGyro_Z",            super.getRawGyroZ());
        SmartDashboard.putNumber(   "RawAccel_X",           super.getRawAccelX());
        SmartDashboard.putNumber(   "RawAccel_Y",           super.getRawAccelY());
        SmartDashboard.putNumber(   "RawAccel_Z",           super.getRawAccelZ());
        SmartDashboard.putNumber(   "RawMag_X",             super.getRawMagX());
        SmartDashboard.putNumber(   "RawMag_Y",             super.getRawMagY());
        SmartDashboard.putNumber(   "RawMag_Z",             super.getRawMagZ());
        SmartDashboard.putNumber(   "IMU_Temp_C",           super.getTempC());
        SmartDashboard.putNumber(   "IMU_Timestamp",        super.getLastSensorTimestamp());
        
        /* Omnimount Yaw Axis Information                                           */
        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
        AHRS.BoardYawAxis yaw_axis = super.getBoardYawAxis();
        SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
        SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
        
        /* Sensor Board Information                                                 */
        SmartDashboard.putString(   "FirmwareVersion",      super.getFirmwareVersion());
        
        /* Quaternion Data                                                          */
        /* Quaternions are fascinating, and are the most compact representation of  */
        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
        /* from the Quaternions.  If interested in motion processing, knowledge of  */
        /* Quaternions is highly recommended.                                       */
        SmartDashboard.putNumber(   "QuaternionW",          super.getQuaternionW());
        SmartDashboard.putNumber(   "QuaternionX",          super.getQuaternionX());
        SmartDashboard.putNumber(   "QuaternionY",          super.getQuaternionY());
        SmartDashboard.putNumber(   "QuaternionZ",          super.getQuaternionZ());
        
        /* Connectivity Debugging Support                                           */
        SmartDashboard.putNumber(   "IMU_Byte_Count",       super.getByteCount());
        SmartDashboard.putNumber(   "IMU_Update_Count",     super.getUpdateCount());
        
    }
}
