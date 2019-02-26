
package frc.robot.util.smartboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import frc.robot.util.*;
import frc.robot.util.VortxSubsystem;

public class SmartBoard {

    public static NetworkTableInstance NTValuesInstance = NetworkTableInstance.getDefault().create();
    public static NetworkTableInstance NTConfigInstance = NetworkTableInstance.getDefault().create();
    public static NetworkTableInstance NTErrorsInstance = NetworkTableInstance.getDefault().create();

    // Classes
    private static final NetworkTable config = NTConfigInstance.getTable("").getSubTable("config");
    private static final NetworkTable values = NTValuesInstance.getTable("").getSubTable("values");
    private static final NetworkTable errors = NTErrorsInstance.getTable("").getSubTable("errors");

    // Return the table where all the values for a specific subsystem should be reported
    public static NetworkTable getValuesTable(VortxSubsystem subsystem) {
        return values.getSubTable(subsystem.name);
    }

    // Return the table where all the config values for a specific subsystem should be reported
    public static NetworkTable getConfigTable(VortxSubsystem subsystem) {
        return config.getSubTable(subsystem.name);
    }

    // Return the table where all the config values for a specific subsystem should be reported
    public static NetworkTable getErrorTable(VortxSubsystem subsystem) {
        return errors.getSubTable(subsystem.name);
    }

    // Return the table for all errors
    public static NetworkTable getErrorTable() {
        return errors;
    }

    // Start the sync of config tables
    public static void setPreMatchMode() {
        NTConfigInstance.startClientTeam(3735);
    }

    // Stop the sync of config tables
    public static void setMatchMode() {
        NTConfigInstance.stopClient();
    }

    public static void start() {
        // Start the sync of all config intances
        // * TODO: Check if this actually is necessary
        NTConfigInstance.startClientTeam(3735);
        NTValuesInstance.startClientTeam(3735);
        NTErrorsInstance.startClientTeam(3735);

        // Set the default value for the master alarm
        errors.getEntry("master").setNumber(0);
    }
}