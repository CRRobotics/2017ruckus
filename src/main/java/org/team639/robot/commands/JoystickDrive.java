package org.team639.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.OI;
import org.team639.robot.Robot;

public class JoystickDrive extends Command {

    private Joystick leftStick;
    private Joystick rightStick;
    private Preferences prefs;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(Robot.driveTrain);

        leftStick = OI.getLeftDriveStick();
        rightStick = OI.getRightDriveStick();

        prefs = Preferences.getInstance();
    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
        int mode = Robot.driveMode.getSelected();
        switch (mode) {
            case 0:
                Robot.driveTrain.tankDrive(leftStick.getY(), rightStick.getY());
                break;
            case 1:
                Robot.driveTrain.arcadeDrive(leftStick.getY(), rightStick.getX());
                break;
            case 2:
                Robot.driveTrain.arcadeDrive(leftStick.getY(), leftStick.getX());
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
