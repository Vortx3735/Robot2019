package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.drive.ZeroEncoders;
import frc.robot.commands.drive.positions.ResetPosition;
import frc.robot.commands.drive.positions.ZeroYaw;
import frc.robot.commands.elevator.ElevatorResetEncoder;

public class ZeroAll extends InstantCommand {

    Command driveEnc;
    Command yawZero;
    Command zeroPos;
    Command zeroElv;

    public ZeroAll() {
        driveEnc = new ZeroEncoders();
        yawZero = new ZeroYaw();
        zeroPos = new ResetPosition();
        zeroElv = new ElevatorResetEncoder();
    }

    @Override
    protected void initialize() {
        driveEnc.start();
        yawZero.start();
        zeroPos.start();
        zeroElv.start();
    }

}