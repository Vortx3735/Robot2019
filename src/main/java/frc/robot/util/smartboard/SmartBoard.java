
package frc.robot.util.smartboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import frc.robot.util.*;
import frc.robot.util.VortxSubsystem;

public class SmartBoard {

    // Classes
    private static final NetworkTable mainTable = NetworkTableInstance.getDefault().getTable("");
    private static final NetworkTable config = mainTable.getSubTable("config");
    private static final NetworkTable values = mainTable.getSubTable("values");
    private static final NetworkTable errors = mainTable.getSubTable("errors");

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
}