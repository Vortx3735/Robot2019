package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.drive.ZeroEncoders;
import frc.robot.commands.drive.positions.ResetPosition;
import frc.robot.commands.drive.positions.ZeroYaw;
import frc.robot.commands.elevator.ElevatorResetEncoder;

public class ZeroAll extends Command {

    Command driveEnc;
    Command yawZero;
    Command zeroPos;
    Command zeroElv;

    public ZeroAll() {
        this.setRunWhenDisabled(true);
        driveEnc = new ZeroEncoders();
        yawZero = new ZeroYaw();
        zeroPos = new ResetPosition();
        zeroElv = new ElevatorResetEncoder();
        System.out.println("Zero all created");
    }

    @Override
    protected void initialize() {
        System.out.println("Zero all inited");
    }

    @Override
    protected void execute() {
        System.out.println("Zerod everything");
        driveEnc.start();
        yawZero.start();
        zeroPos.start();
        zeroElv.start();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}