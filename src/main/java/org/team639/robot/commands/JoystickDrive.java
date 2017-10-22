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
        requires(Robot.driveTrain);

        leftStick = OI.getLeftDriveStick();
        rightStick = OI.getRightDriveStick();
    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
        Robot.driveTrain.tankDrive(leftStick.getY(), rightStick.getY());
    }

    /**
     * Returns whether or not the command is finished
     * @return whether or not the command is finished
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}
