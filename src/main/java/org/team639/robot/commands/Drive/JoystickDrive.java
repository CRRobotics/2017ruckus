package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.Constants;
import org.team639.robot.OI;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

public class JoystickDrive extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick stick;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(driveTrain);

//        leftStick = OI.getLeftDriveStick();
//        rightStick = OI.getRightDriveStick();
//        stick = OI.getStick();
    }

    protected void initialize() {
        double p = Constants.DriveTrain.DRIVE_P;
        double i = Constants.DriveTrain.DRIVE_I;
        double d = Constants.DriveTrain.DRIVE_D;
        driveTrain.setPID(p,i,d);

        if (Robot.getTalonMode() != driveTrain.getCurrentControlMode()) {
            driveTrain.setCurrentControlMode(Robot.getTalonMode());
        }
    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
//        System.out.println("In JoystickDrive.execute()");
        DriveTrain.DriveMode mode = Robot.getDriveMode(); //Get drive mode from SmartDashboard
        switch (mode) {
            case TANK:
                driveTrain.tankDrive(OI.manager.getLeftDriveY(), OI.manager.getRightDriveY());
                break;
            case ARCADE_1_JOYSTICK:
                driveTrain.arcadeDrive(OI.manager.getRightDriveY(), OI.manager.getRightDriveX());
                break;
            case ARCADE_2_JOYSTICK:
                driveTrain.arcadeDrive(OI.manager.getRightDriveY(), OI.manager.getLeftDriveX());
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
