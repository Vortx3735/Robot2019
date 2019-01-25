package frc.robot.commands.drive.profiling;

import frc.robot.Robot;
import frc.robot.Constants.PathFinder;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.util.cmds.VortxCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class PathFollower extends VortxCommand {

    public EncoderFollower lFollower;
    public EncoderFollower rFollower;

    public double left;
    public double right;
    public double angle;
    public double desiredAngle;
    public double angleDifference;
    public double turn;


    private int i=0;

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

        lFollower = new EncoderFollower(leftTraj);
        rFollower = new EncoderFollower(rightTraj);

        lFollower.configureEncoder((int)(Math.round(Robot.drive.getLeftTicks())), Constants.Drive.ticksPerRotation, Constants.Drive.wheelDiam);
        rFollower.configureEncoder((int)(Math.round(Robot.drive.getRightTicks())), Constants.Drive.ticksPerRotation, Constants.Drive.wheelDiam);

        lFollower.configurePIDVA(0, 0, 0, 1/Constants.Drive.maxVelocity, 0);
        rFollower.configurePIDVA(0, 0, 0, 1/Constants.Drive.maxVelocity, 0);

        System.out.println("Set trajectories");

        requires(Robot.drive);

        while(!lFollower.isFinished()||!rFollower.isFinished()) {
            System.out.println("While loop iterated");
            i++;
            left = lFollower.calculate((int)(Math.round(Robot.drive.getLeftTicks()))); 
            right = rFollower.calculate((int)(Math.round(Robot.drive.getRightTicks()))); 

            System.out.println(i + " Left: " + left + " Right: " + right);
            angle = 0;// Robot.navigation.getYaw();
            desiredAngle = Pathfinder.r2d(lFollower.getHeading());
            angleDifference = Pathfinder.boundHalfDegrees(desiredAngle - angle);

            turn = 0.8 * (-1.0/80) * angleDifference;

            Robot.drive.setLeftRight(left+turn, right-turn);
        }
        
    }

    @Override
    protected void execute () {
        System.out.println("Execute was called");
        
        
    }

    

    @Override
    protected boolean isFinished() {
        System.out.println("Is finished is called");
        return !lFollower.isFinished()||!rFollower.isFinished();
    }

}