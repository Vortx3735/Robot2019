package frc.robot.commands.drive.profiling;

import frc.robot.Robot;
import frc.robot.Constants.PathFinder;
import frc.robot.Constants;
import frc.robot.util.cmds.VortxCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathFollower extends VortxCommand {

    public Trajectory leftTraj;
    public Trajectory rightTraj;

    public EncoderFollower lFollower;
    public EncoderFollower rFollower;

    public double left;
    public double right;
    public double angle;
    public double desiredAngle;
    public double angleDifference;
    public double turn;


    //@param: array of waypoint that have (x,y,0)
    public PathFollower(Waypoint[] waypoints) {

        //FitMethod fit, int samples, double dt, double max_velocity, double max_acceleration, double max_jerk
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, //Fit method
        Trajectory.Config.SAMPLES_HIGH //How many samples to use to refine the path (higher = smoother, lower = faster)
        , Constants.dt, Constants.Drive.maxVelocity, Constants.Drive.maxAccel, Constants.Drive.maxJerk); // dt, max_velocity,  max_acceleration,  max_jerk

        Trajectory trajectory = Pathfinder.generate(waypoints, config);

        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(Constants.Drive.wheelBase); //wheel base in meters

        // Do something with the new Trajectories...
        Trajectory leftTraj = modifier.getLeftTrajectory();
        Trajectory rightTraj = modifier.getRightTrajectory();
        //pass trajectories
        this.leftTraj = leftTraj;
        this.rightTraj = rightTraj;

        lFollower.setTrajectory(leftTraj);
        rFollower.setTrajectory(rightTraj);

        lFollower.configureEncoder((int)(Math.round(Robot.drive.getLeftTicks())), Constants.Drive.ticksPerRotation, Constants.Drive.wheelDiam);
        rFollower.configureEncoder((int)(Math.round(Robot.drive.getRightTicks())), Constants.Drive.ticksPerRotation, Constants.Drive.wheelDiam);

        requires(Robot.drive);
        
    }
    
    public void setPIDVA(double kp, double ki, double kd, double kv, double ka) {
        lFollower.configurePIDVA(kp, ki, kd, kv, ka);
        rFollower.configurePIDVA(kp, ki, kd, kv, ka);
    }   

    public void followPath () {
        while(!lFollower.isFinished()||!rFollower.isFinished()) {
            left = lFollower.calculate((int)(Math.round(Robot.drive.getLeftTicks()))); 
            right = rFollower.calculate((int)(Math.round(Robot.drive.getRightTicks()))); 

            angle = Robot.navigation.getYaw();
            desiredAngle = Pathfinder.r2d(lFollower.getHeading());
            angleDifference = Pathfinder.boundHalfDegrees(desiredAngle - angle);

            turn = 0.8 * (-1.0/80) * angleDifference;

            Robot.drive.setLeftRight(left+turn, right-turn);
        }
    }

    

    @Override
    protected boolean isFinished() {
        return false;
    }

}