package frc.robot.util.smartboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.util.VortxSubsystem;

public class SmartBoardError {

    String id;
    VortxSubsystem subsystem;
    int identifier;
    SmartBoardErrorType type;

    boolean isActiviated = false;

    public SmartBoardError(VortxSubsystem s, int i, SmartBoardErrorType t) {
        
        // Store values
        subsystem = s;
        identifier = i;
        type = t;

        // Create unqiue ID
        id = subsystem.id + String.format("%01d",identifier);

        // Add to NetworkTables
        subsystem.errorTable.getEntry(id).setBoolean(false);

    }

    public void report()
    {
        subsystem.errorTable.getEntry(id).setBoolean(true);
        Shuffleboard.addEventMarker(id + " REPORTED", EventImportance.kHigh);
    }

    
}