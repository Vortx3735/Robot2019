package frc.robot.util.oi;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class VorTXControllerButton extends Button {

    public Command _whenPressedCommand;
    public Command _whileHeldCommand;
    public Command _whenReleasedCommand;

    VorTXControllerButton() {}

    @Override
    public boolean get() {
        return false;
    }
    
    @Override
    public void whenPressed(Command command) {
        super.whenPressed(command);
        _whenPressedCommand = command;
    }

    @Override
    public void whileHeld(Command command) {
        super.whileHeld(command);
        _whileHeldCommand = command;
    }

    @Override
    public void whenReleased(Command command) {
        super.whenReleased(command);
        _whenReleasedCommand = command;
    }

}