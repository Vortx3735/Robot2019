package frc.robot.util.smartboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class SmartBoardEntry<T> {

    private NetworkTableEntry _entry;
    private T _defaultValue;

    public SmartBoardEntry(NetworkTable table, String key, T defaultValue) {
        
        _entry = table.getEntry(key);
        _defaultValue = defaultValue;

        if (table.getPath().contains("config")) {
            _entry.setPersistent();
        }

        if (_entry.exists() == false) {
            set(defaultValue);
        }
    }

    public Number getNumber() {
        return _entry.getNumber((Number)_defaultValue);
    }

    public String getString() {
        return _entry.getString((String)_defaultValue);
    }

    public boolean getBooleam() {
        return _entry.getBoolean((boolean)_defaultValue);
    }

    public void set(T val) {
        _entry.setValue(val);
    }

}