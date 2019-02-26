package frc.robot.commands.auto;


import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.profiling.DriveToTargetP;
import frc.robot.commands.drive.profiling.PathFollower;
import jaci.pathfinder.Waypoint;

public class MiddleRightHatch extends CommandGroup{


    public MiddleRightHatch() {
        Waypoint[] waypoints = new Waypoint[2];

        //TODO: Pregenerate the paths and have them saved
        waypoints[0] = new Waypoint(0, 0, 0);
        waypoints[1] = new Waypoint(140, 20, 0);

        addSequential(new PathFollower(waypoints));

        addSequential(new DriveToTargetP());
    }
}