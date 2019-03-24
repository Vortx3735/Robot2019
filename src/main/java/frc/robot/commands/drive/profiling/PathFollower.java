package frc.robot.commands.drive.profiling;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.cmds.VortxCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class PathFollower extends VortxCommand {

    public DistanceFollower lFollower;
    public DistanceFollower rFollower;

    public Trajectory leftTraj;
    public Trajectory rightTraj;

    public double left;
    public double right;
    public double angle;
    public double desiredAngle;
    public double angleDifference;
    public double turn;

    public double originalAngleOffset;

    public double originalLeftOffset;
    public double originalRightOffset;

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

        lFollower = new DistanceFollower(leftTraj);
        rFollower = new DistanceFollower(rightTraj);
        

        lFollower.configurePIDVA(.01, 0, 0, 1/Constants.Drive.maxVelocity, .005);//1/Constants.Drive.maxAccel*.08);
        rFollower.configurePIDVA(.01, 0, 0, 1/Constants.Drive.maxVelocity, .005);//1/Constants.Drive.maxAccel*.08);

        long timeTake = System.currentTimeMillis()-startTime;

        System.out.println("Set trajectories in " + timeTake  + "millis");

        originalAngleOffset = Robot.navigation.getYaw();
        originalLeftOffset = Robot.drive.getLeftPosition();
        originalRightOffset = Robot.drive.getRightPosition();

        // for(int i = 0; i<leftTraj.segments.length; i++) {
        //     System.out.println(i*.02 + " " + leftTraj.segments[i].velocity);
        // }
    }

    @Override
    protected void execute () {

            double actualLeft = Robot.drive.getLeftPosition() - originalLeftOffset;
            double actualRight = Robot.drive.getRightPosition() - originalRightOffset;

           //SmartDashboard.putNumber("Error left", wantedLeft-actualLeft);
           //SmartDashboard.putNumber("Error right", wantedRight-actualRight);

           //SmartDashboard.putNumber("Error Vel left", lFollower.getSegment().velocity-Robot.drive.getLeftSpeed());
           //SmartDashboard.putNumber("Error Vel right", rFollower.getSegment().velocity-Robot.drive.getRightSpeed());

            //System.out.println(actualLeft + " " + actualRight);

            //SmartDashboard.putNumber("Distance Covered Left", actualLeft);
            //SmartDashboard.putNumber("Distance Covered Right", actualRight);

            left = lFollower.calculate(actualLeft); 
            right = rFollower.calculate(actualRight);

            angle = Robot.navigation.getYaw();
            desiredAngle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(lFollower.getHeading()) + originalAngleOffset);
            angleDifference = Pathfinder.boundHalfDegrees(desiredAngle - angle);

            System.out.println("Navx: " + angle + " Offset: " + originalAngleOffset + " desired: " + desiredAngle + " Difference: " + angleDifference);
            


            turn = 0;// .01 * angleDifference;

            System.out.println("Left Power: " + left +  " Right Power: " + right  + " Turn power: " + turn);


            Robot.drive.setLeftRight((left+turn), (right-turn));
    }

    

    @Override
    protected boolean isFinished() {
        return rFollower.isFinished() && lFollower.isFinished();
    }

}