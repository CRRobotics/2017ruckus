package org.team639.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.OI;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

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
        DriveTrain.DriveMode mode = Robot.driveMode.getSelected(); //Get drive mode from SmartDashboard
        switch (mode) {
            case TANK:
                Robot.driveTrain.tankDrive(stick.getRawAxis(1), stick.getRawAxis(3));
                break;
            case ARCADE_1_JOYSTICK:
                Robot.driveTrain.arcadeDrive(stick.getRawAxis(3), stick.getRawAxis(2));
                break;
            case ARCADE_2_JOYSTICK:
                Robot.driveTrain.arcadeDrive(leftStick.getRawAxis(0), leftStick.getRawAxis(3));
                break;
        }
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
