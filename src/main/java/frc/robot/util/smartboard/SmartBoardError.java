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
        subsystem.errorTable.getEntry(id).setNumber(0);

    }

    public void report()
    {
        int numRepresentation = numberFromType(type);
        subsystem.errorTable.getEntry(id).setNumber(numRepresentation);
        Shuffleboard.addEventMarker(id + " REPORTED", EventImportance.kHigh);

        subsystem.errorTable.getEntry(subsystem.id).setNumber(numRepresentation);

        if (type == SmartBoardErrorType.NUCLEAR) {
            SmartBoard.getErrorTable().getEntry("master").setNumber(2);
        }
    }

    public static int numberFromType(SmartBoardErrorType t) 
    {
        switch (t) 
        {
            case WARNING: return 1;
            case ERROR: return 2;
            case NUCLEAR: return 3;
            default: return 1;
        }
    }
}