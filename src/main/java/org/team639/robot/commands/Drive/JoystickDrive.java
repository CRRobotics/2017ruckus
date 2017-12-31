package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.controls.LogitechF310;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Constants;
import org.team639.robot.OI;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.DriveTrain.*;

public class JoystickDrive extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private PID turnPID;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(driveTrain);
    }

    protected void initialize() {
        double dP = DRIVE_P;
        double dI = DRIVE_I;
        double dD = DRIVE_D;
        driveTrain.setPID(dP,dI,dD);

        double p = SmartDashboard.getNumber("drive p", DRIVE_P);
        double i = SmartDashboard.getNumber("drive i", DRIVE_I);
        double d = SmartDashboard.getNumber("drive d", DRIVE_I);
        double rate = SmartDashboard.getNumber("rate", 0.1);
        double tolerance = SmartDashboard.getNumber("tolerance", 200);
        double min = SmartDashboard.getNumber("min", 0.2);
        double max = SmartDashboard.getNumber("max", 0.5);
        double iCap = SmartDashboard.getNumber("iCap", 0.2);
        turnPID = new PID(p, i, d, min, max, rate, tolerance, iCap);

        if (Robot.getTalonMode() != driveTrain.getCurrentControlMode()) {
            driveTrain.setCurrentControlMode(Robot.getTalonMode());
        }
    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
        DriveTrain.DriveMode mode;
        double x;
        double y;
        double speed;
        double angle;
        if (OI.manager.getButtonPressed(LogitechF310.Buttons.LB)) {
            mode = DriveTrain.DriveMode.FIELD_1_JOYSTICK;
        } else {
            mode = Robot.getDriveMode(); //Get drive mode from SmartDashboard
        }
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
            case FIELD_1_JOYSTICK:
                x = OI.manager.getRightDriveX();
                y = OI.manager.getRightDriveY();
                angle = Math.abs(x) >= Constants.JOYSTICK_DEADZONE || Math.abs(y) >= Constants.JOYSTICK_DEADZONE ? OI.manager.getRightDriveAngle() : 500;
                speed = Math.sqrt(Math.pow(y, 2) * Math.pow(x, 2));
                fieldOrientedDrive(angle, speed);
                break;
            case FIELD_2_JOYSTICK:
                x = OI.manager.getLeftDriveX();
                y = OI.manager.getLeftDriveY();
                angle = Math.abs(x) >= Constants.JOYSTICK_DEADZONE || Math.abs(y) >= Constants.JOYSTICK_DEADZONE ? OI.manager.getLeftDriveAngle() : 500;
                fieldOrientedDrive(angle, OI.manager.getRightDriveY());
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

    /**
     * Drives the robot in a field oriented manner
     * @param angle The angle to drive in. Angles greater than 360 represent no turning.
     * @param speed The speed to drive at.
     */
    private void fieldOrientedDrive(double angle, double speed) {
        double output;
        if (speed > 1) speed = 1;
        if (angle > 360) {
            double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
            output = turnPID.compute(error);
        } else {
            output = 0;
        }
        driveTrain.setSpeedsPercent(speed / 2 - output, speed / 2 + output);
    }
}
