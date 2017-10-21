package org.team639.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.OI;
import org.team639.robot.Robot;

public class JoystickDrive extends Command {

    private Joystick leftStick;
    private Joystick rightStick;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(Robot.drive);

        leftStick = OI.getLeftDriveStick();
        rightStick = OI.getRightDriveStick();
    }

    protected void execute() {
        Robot.drive.tankDrive(leftStick.getY(), rightStick.getY());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
