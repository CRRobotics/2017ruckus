package org.team639.robot.commands;

import com.ctre.Drive.Styles;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.Constants;
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

    protected void initialize() {

    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.D);
        Robot.driveTrain.setPID(p,i,d);

        if (Robot.talonMode.getSelected() != Robot.driveTrain.getCurrentControlMode()) {
            Robot.driveTrain.setCurrentControlMode(Robot.talonMode.getSelected());
        }

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
