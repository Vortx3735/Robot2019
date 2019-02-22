package frc.robot.commands.drive.profiling;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.cmds.VortxCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class PathFollower extends VortxCommand {

    public EncoderFollower lFollower;
    public EncoderFollower rFollower;

    // public DistanceFollower lFollower;
    // public DistanceFollower rFollower;

    public Trajectory leftTraj;
    public Trajectory rightTraj;

    public double left;
    public double right;
    public double angle;
    public double desiredAngle;
    public double angleDifference;
    public double turn;

    public double originalAngleOffset;

    Waypoint[] waypoints;

    //@param: array of waypoint that have (x,y,0)
    public PathFollower(Waypoint[] waypoints) {
        
        this.waypoints = waypoints;

        requires(Robot.drive);            
        
    }

    @Override
    protected void initialize() {

        long startTime = System.currentTimeMillis();

        //FitMethod fit, int samples, double dt, double max_velocity, double max_acceleration, double max_jerk
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, //Fit method
        Trajectory.Config.SAMPLES_FAST //How many samples to use to refine the path (higher = smoother, lower = faster)
        , Constants.dt, Constants.Drive.maxVelocity, Constants.Drive.maxAccel, Constants.Drive.maxJerk); // dt, max_velocity,  max_acceleration,  max_jerk

        Trajectory trajectory = Pathfinder.generate(waypoints, config);

        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(Constants.Drive.wheelBase); //wheel base in meters

        // Do something with the new Trajectories...
        leftTraj = modifier.getLeftTrajectory();
        rightTraj = modifier.getRightTrajectory();

        lFollower = new EncoderFollower(leftTraj);
        rFollower = new EncoderFollower(rightTraj);

        
        lFollower.configureEncoder((int)(Math.round(Robot.drive.getLeftPosition())), Constants.Drive.ticksPerRotation, Constants.Drive.wheelDiam);
        rFollower.configureEncoder((int)(Math.round(Robot.drive.getRightPosition())), Constants.Drive.ticksPerRotation, Constants.Drive.wheelDiam);

        lFollower.configurePIDVA(.00055, 0, 0, 1/Constants.Drive.maxVelocity, 1/Constants.Drive.maxAccel*.01);//1/Constants.Drive.maxAccel*.08);
        rFollower.configurePIDVA(.00055, 0, 0, 1/Constants.Drive.maxVelocity, 1/Constants.Drive.maxAccel*.01);//1/Constants.Drive.maxAccel*.08);

        long timeTake = System.currentTimeMillis()-startTime;

        System.out.println("Set trajectories in " + timeTake  + "millis");

        originalAngleOffset = Robot.navigation.getYaw();

        System.out.println("The original angle off set is " + originalAngleOffset);
    }

    @Override
    protected void execute () {
            left = lFollower.calculate((int)Robot.drive.getLeftPosition()); 
            right = rFollower.calculate((int)Robot.drive.getRightPosition());
            


            angle = Robot.navigation.getYaw();
            desiredAngle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(lFollower.getHeading()) + originalAngleOffset);
            angleDifference = Pathfinder.boundHalfDegrees(desiredAngle - angle);

            System.out.println("Navx: " + angle + " Offset: " + originalAngleOffset + " desired: " + desiredAngle + " Difference: " + angleDifference);
            


            turn = -1.7 * (1.0/80) * angleDifference;

            System.out.println("Left Power: " + left +  " Right Power: " + right  + " Turn power: " + turn);


            Robot.drive.setLeftRight((left+turn), (right-turn));
    }

    

    @Override
    protected boolean isFinished() {
        return rFollower.isFinished() && lFollower.isFinished();
    }

}