package frc.robot.util.hardware;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VortxPDP extends PowerDistributionPanel {

    public boolean isBrowningOut() {
        return getVoltage() < 6.8;
    }

    public void log() {
        SmartDashboard.putNumber("PDP POWER", getTotalPower());
        SmartDashboard.putNumber("PDP CURRENT", getTotalCurrent());
    }

}