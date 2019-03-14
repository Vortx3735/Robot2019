package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.profiling.PathFollower;
import jaci.pathfinder.Waypoint;

public class InchesStraight extends CommandGroup{

    public InchesStraight(double x, double y) {
        System.out.println("Meter straight called");
        Waypoint[] waypoints = new Waypoint[2];
        waypoints[0] = new Waypoint(0, 0, 0);
        waypoints[1] = new Waypoint(x, y, 0);
        System.out.println("Creating new waypoints");
        addSequential(new PathFollower(waypoints), 10);
    }
}