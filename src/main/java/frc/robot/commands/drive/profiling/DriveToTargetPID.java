package frc.robot.commands.drive.profiling;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.settings.Func;

/**
 *
 */
public class DriveToTargetPID extends Command {
	
	// private double finishTime = .3;
	// private double timeOnTarget = 0;

    Func getAngleError;
    Func getDistanceError;
    int count;
    
    PIDController turningController;
    PIDController moveController;

    double turn;
    double move;

    double squaredSum;
	
	public DriveToTargetPID() {
    	this(new Func(){
			@Override
			public double getValue() {
				return VortxMath.navLimit(Robot.navigation.getYaw()+Robot.limelight.getTx());
			}
    	}, new Func() {
            @Override
            public double getValue() {
                return Robot.arduino.getDistance();
            }
        });
    }

	public DriveToTargetPID(Func angle, Func distance) {
    	requires(Robot.drive);
		requires(Robot.navigation);
        requires(Robot.limelight);
        requires(Robot.arduino);
        
        createControllers();
       
        getAngleError = angle;
        getDistanceError = distance;
		count = 0;
    }
    
    public void createControllers() {

        turningController = new PIDController(.02, 0, .001, 
        new PIDSource() {

            PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
        
            @Override
                public double pidGet() {
                    return Robot.navigation.getYaw();
                }
            
            @Override
                public void setPIDSourceType(PIDSourceType pidSource) {
                    m_sourceType = pidSource;
                }
            
            @Override
            public PIDSourceType getPIDSourceType() {
                return m_sourceType;
            }
        }, new PIDOutput() {

            @Override
            public void pidWrite(double output) {  
                turn = output;
            }      
        });

        moveController = new PIDController(.01, 0, 0, 
        new PIDSource() {

            PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
        
            @Override
                public double pidGet() {
                    return getDistanceError.getValue();
                }
            
            @Override
                public void setPIDSourceType(PIDSourceType pidSource) {
                    m_sourceType = pidSource;
                }
            
            @Override
            public PIDSourceType getPIDSourceType() {
                return m_sourceType;
            }
        }, new PIDOutput() {

            @Override
            public void pidWrite(double output) {        
                move = output;
            }      
        });

        SmartDashboard.putData(turningController);
        SmartDashboard.putData(moveController);

        turningController.setInputRange(-180, 180);
		turningController.setOutputRange(-.3, .3);
    	turningController.setContinuous();
        turningController.setAbsoluteTolerance(1);
        
        moveController.setInputRange(0, 100);
        moveController.setOutputRange(-.7, .7);
        //moveController.setContinuous(); //this breaks to error for some reason
        moveController.setAbsoluteTolerance(1);
        
    }
	

    // Called just before this Command runs the first time
    protected void initialize() {
    	turningController.setSetpoint(getAngleError.getValue());
        moveController.setSetpoint(0); //Always get upto 24 inches away
        
        turningController.enable();
        moveController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   

        squaredSum += getAngleError.getValue() * getAngleError.getValue();
        
		count++;
		if(count%5==0) {
            //System.out.println("New angle setpoint is " + getAngleError.getValue());
            turningController.setSetpoint(Math.sqrt(squaredSum/count));	
            count = 0;
        } 
        //System.out.println("Distance error is " + moveController.getError());
        if(Math.abs(moveController.getError())<24) {
            move = 0;
            System.out.println("We are witin 24 inches");
        }
        SmartDashboard.putNumber("move", move);
        SmartDashboard.putNumber("turn", turn);
        Robot.drive.setLeftRight(move-turn, move+turn);
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
		//return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
        turningController.disable();
        moveController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}