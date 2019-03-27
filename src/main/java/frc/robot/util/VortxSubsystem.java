package frc.robot.util;

import frc.robot.util.smartboard.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;

public abstract class VortxSubsystem extends Subsystem {

    public final String name;
    public final String id;
    public final NetworkTable configTable;
    public final NetworkTable valueTable;
    public final NetworkTable errorTable;

    public SmartBoardError ERROR_GENERAL;

    public VortxSubsystem(String n, String i) {
        // Store values
        name = n;
        id = i;
        
        // Configure Tables
        configTable = SmartBoard.getConfigTable(this);
        valueTable = SmartBoard.getValuesTable(this);
        errorTable = SmartBoard.getErrorTable(this);

        // Create default error
        ERROR_GENERAL = new SmartBoardError(this, 0, SmartBoardErrorType.ERROR);
    }

}