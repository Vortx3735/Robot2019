
package frc.robot.util.smartboard;
import javax.management.NotCompliantMBeanException;

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

    // Subsystems
    public static NetworkTable getValuesTable(VortxSubsystem subsystem) {
        return values.getSubTable(subsystem.name);
    }

    public static NetworkTable getConfigTable(VortxSubsystem subsystem) {
        return config.getSubTable(subsystem.name);
    }

    public static NetworkTable getErrrorTable(VortxSubsystem subsystem) {
        return errors.getSubTable(subsystem.name);
    }

    public static void setPreMatchMode() {
        // Start the sync of config files
        NTConfigInstance.startClientTeam(3735);
    }

    public static void setMatchMode() {
        // Stop the sync of config files
        NTConfigInstance.stopClient();
    }

    public static void start() {
        NTConfigInstance.startClientTeam(3735);
        NTValuesInstance.startClientTeam(3735);
        NTErrorsInstance.startClientTeam(3735);
    }
}