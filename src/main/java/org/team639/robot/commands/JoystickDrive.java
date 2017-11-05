package org.team639.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.OI;
import org.team639.robot.Robot;

public class JoystickDrive extends Command {

    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick stick;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(Robot.driveTrain);

//        leftStick = OI.getLeftDriveStick();
//        rightStick = OI.getRightDriveStick();
        stick = OI.getStick();
    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
        int mode = Robot.driveMode.getSelected();
        switch (mode) {
            case 0:
                Robot.driveTrain.tankDrive(stick.getY(), stick.getY());
                break;
            case 1:
                Robot.driveTrain.arcadeDrive(stick.getRawAxis(3), stick.getRawAxis(2));
                break;
            case 2:
                Robot.driveTrain.arcadeDrive(leftStick.getRawAxis(0), leftStick.getRawAxis(3));
                break;
        }
//        Robot.driveTrain.tankDrive(leftStick.getY(), rightStick.getY());
//        Robot.driveTrain.arcadeDrive(rightStick.getY(), leftStick.getX());
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
