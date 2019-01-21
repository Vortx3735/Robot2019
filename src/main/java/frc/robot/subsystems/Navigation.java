package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.settings.Waypoints;
import frc.robot.util.PIDCtrl;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.hardware.VortxAhrs;
import frc.robot.util.profiling.Location;
import frc.robot.util.profiling.Position;
import frc.robot.util.profiling.Ray;
import frc.robot.util.settings.Setting;

//import Robot.Side;


public class Navigation extends Subsystem implements PIDSource, PIDOutput {
	private static final int BUMP_THRESHOLD = 1;

	private VortxAhrs ahrs;
	
	private PIDCtrl controller;
	//PID Controller stuff
	private static Setting outputExponent = new Setting("Nav Output Exponent", 1, false);
    public static Setting actingI = 		new Setting("Nav Acting I Value", 0.004, false);
    
//    public static Setting InitialOffset =	new Setting("Horizontal Offset", 0);
    
	public static Setting navCo = 			new Setting("Nav Assist Coeffecient", 7, false);

	
	Position pos = new Position(0,0,0);
	private Object posLock = new Object();
	
	NetworkTable table;

	private double prevLeft = 0;
	private double prevRight = 0;
	private double curLeft;
	private double curRight;
	
	public Navigation(){
		table = NetworkTableInstance.getDefault().getTable("MAP");
		
		ahrs = new VortxAhrs(SPI.Port.kMXP);
		controller = new PIDCtrl(.016,0.0,0.061,this,this, 10);
    	controller.setOutputRange(-.7, .7);
    	controller.setInputRange(-180, 180);
    	controller.setContinuous();
    	controller.setAbsoluteTolerance(3);
    	SmartDashboard.putData("Turning Controller", controller);
    	
		curLeft = Robot.drive.getLeftPosition();
    	curRight = Robot.drive.getRightPosition();
    	prevLeft = curLeft;
    	prevRight = curRight;
	}

	public synchronized void setPosition(Position p){
		synchronized(posLock){
			pos = p;
		}
		ahrs.setYaw(p.yaw);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    }
    
    public synchronized void integrate(){
    	synchronized(posLock){
    		curLeft = Robot.drive.getLeftPosition();
        	curRight = Robot.drive.getRightPosition();
        	
        	double dd = ((curLeft-prevLeft) + (curRight-prevRight)) * .5;
        	
        	pos.yaw = getYaw();
        	double angle = VortxMath.swapYawAngle(pos.yaw);

    		pos.x += Math.cos(Math.toRadians(angle)) * dd;
    		pos.y += Math.sin(Math.toRadians(angle)) * dd;
    		
        	prevLeft = curLeft;
        	prevRight = curRight;
        	
    	}
    	
    	
    }
    
    public double getYaw(){
    	return ahrs.getYaw();
    }
    
    public void setYaw(double offset) {
    	ahrs.setYaw(offset);
    }
    
    public Ray getRay() {
    	return new Ray(getPosition(), getYaw());
    }
    
    public void zeroYaw(){
    	ahrs.zeroYaw();
    }
    public void resetAhrs(){
    	ahrs.reset();
    }
    public double getRate(){
    	return ahrs.getRate();
    }
    
    public AHRS getAHRS(){
    	return ahrs;
    }
    public void log(){
    	SmartDashboard.putNumber("Robot Yaw", getYaw());
    	SmartDashboard.putNumber("Nav Loc X", pos.x);
    	SmartDashboard.putNumber("Nav Loc Y", pos.y);
    	SmartDashboard.putNumber("Nav Acc", this.getXYAcceleration());
//    	SmartDashboard.putNumber("Gyro Acceleration X", ahrs.getWorldLinearAccelX());
//    	SmartDashboard.putNumber("Gyro Acceleration Y", ahrs.getWorldLinearAccelY());
//    	SmartDashboard.putNumber("Gyro Accel XY Vector", getXYAcceleration());

 //     displayDebugGyroData();

    }
    
    public void displayPosition(){
    	table.getEntry("CenterX").setDoubleArray(new double[]{pos.x});
    	table.getEntry("CenterY").setDoubleArray(new double[]{pos.y});
    	table.getEntry("Yaw").setDoubleArray(new double[]{pos.yaw});

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
		return ahrs.getYaw();
	}


	public PIDCtrl getController() {
		return controller;
	}


	@Override
	public void pidWrite(double output) {
//		output = VortxMath.curve(output, outputExponent.getValue());
		Robot.drive.setLeftRight(output, -output);
	}


	public boolean isBumped() {
		return getXYAcceleration() > BUMP_THRESHOLD;
	}
	
	public double getXYAcceleration(){
		return Math.hypot(ahrs.getWorldLinearAccelY(), ahrs.getWorldLinearAccelX());
	}
	


	public void debugLog() {
		
	}

	public synchronized void resetPosition(Position p) {
		Robot.drive.resetEncodersPositions();
		setPosition(p);
		
		prevLeft = 0;
		prevRight = 0;
		
		System.out.println("Reseting Position...");
	}

	public synchronized void resetPosition() {
		
	}
	
	public synchronized Position getPosition() {
		return pos;
	}
	
	/**
	 * 
	 * @return	the quadrant the robot is in
	 * 			follows the quadrant naming system of algebra, looking at the field from Field Map.PNG
	 */
	public int getQuadrant() {
		double xdif = pos.x - Waypoints.Verticies.center.x;
		double ydif = pos.y - Waypoints.Verticies.center.y;
		if(xdif > 0) {
			if(ydif > 0) {
				return 1;
			}else {
				return 4;
			}
		}else {
			if(ydif > 0) {
				return 2;
			}else {
				return 3;
			}
		}

	}
	public Location getClosestLocation(Location[] locs) {
		if(locs != null && locs.length > 0) {
			Location curPos = getPosition();
			double least = curPos.distanceFrom(locs[0]);
			int best = 0;
			for(int i = 1; i < locs.length; i++) {
				double dist = curPos.distanceFrom(locs[i]);
				if(dist < least) {
					least = dist;
					best = i;
				}
			}
			return locs[best];
		}else {
			System.out.println("Error in getting closest location");
			return new Location(0,0);
		}
	}
	
	public double getAngleToLocation(Location loc) {
		return VortxMath.swapYawAngle(getYawToLocation(loc));
	}
	
	public double getYawToLocation(Location loc) {
		return pos.yawTo(loc);
	}
	
	public void PIDAuto(){
		controller = new PIDCtrl(.02,0.0,0.061,this,this, 10);
    	controller.setOutputRange(-.7, .7);
    	controller.setInputRange(-180, 180);
    	controller.setContinuous();
    	controller.setAbsoluteTolerance(3);	
	}
	
	public void PIDNormal(){
		controller = new PIDCtrl(.016,0.0,0.061,this,this, 10);
    	controller.setOutputRange(-.7, .7);
    	controller.setInputRange(-180, 180);
    	controller.setContinuous();
    	controller.setAbsoluteTolerance(3);
	}
	
    
}

