package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.calc.DDxLimiter;
import org.usfirst.frc.team3735.robot.util.calc.Range;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSetPosDDx extends Command {
	Func targetPos;
	
	double dy;
//	double k;

//	final double startingPower = .4;
	Func maxPower;		
	double minPct = .08;
	
	DDxLimiter limiter;
	//If we are going down, we can go 1.1 at max, because we are adding
	//say .1 to the value in Elevator.java, so we can go "1.1" down, but not only .9 up
	
//    public ElevatorSetPosDDx(double inches) {
//    	this(Func.getFunc(inches));
//    	
//    	
//    }
//    
//    public ElevatorSetPosDDx(Func f) {
//    	this(f, Func.getFunc(.7));
//    }
//    public ElevatorSetPosDDx(double f, Func maxPower) {
//    	this(Func.getFunc(f), maxPower);
//    }
    public ElevatorSetPosDDx(Func targetPos, Func maxPower, Func acc) {
    	this.targetPos = targetPos;
    	this.maxPower = maxPower;
    	
    	limiter = new DDxLimiter(0, new Range(acc));
    	requires(Robot.elevator);
    }
    
    public ElevatorSetPosDDx(double targetPos, double maxPower, double acc) {
    	this(Func.getFunc(targetPos), Func.getFunc(maxPower), Func.getFunc(acc));
    }

    
    public ElevatorSetPosDDx(double pos) {
    	this(Func.getFunc(pos), Func.getFunc(.8), Func.getFunc(.03));
    }

    
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	dy = calcDy();
    }
    
	public double calcDy() {
		return targetPos.getValue() - Robot.elevator.getPosition();
	}
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println(getPower());
    	double pow;
    	double pval = .2;
    	if(dy < 0) {
    		pval = .4;
    	}
    	if(Math.abs(calcDy()) < pval * Math.abs(dy)) {
        	pow = Math.signum(calcDy()) * Math.abs(limiter.feed(Math.signum(dy) * minPct));

    	}else {
    		
        	pow = Math.signum(calcDy()) * Math.abs(limiter.feed(maxPower.getValue()));

    	}
    	
    	Robot.elevator.setPOutputAdjusted(pow);
    	System.out.println("DDx: " + pow);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.signum(calcDy()) != Math.signum(dy);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setPOutput(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
