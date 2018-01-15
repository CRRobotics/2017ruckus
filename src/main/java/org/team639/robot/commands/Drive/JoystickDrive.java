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

/**
 * Controls DriveTrain with Joysticks.
 * Default DriveTrain command.
 */
public class JoystickDrive extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private PID turnPID;
    private double arcadeAngle;

    private double lastSetpointSpeed = 0;
    private double lastSetpointTurning = 0;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(driveTrain);
        arcadeAngle = driveTrain.getRobotYaw();
    }

    protected void initialize() {
        // Field oriented drive turning PID constants from Constants.DriveTrain, prefixed with FOT_
        double p = FOT_P;
        double i = FOT_I;
        double d = FOT_D;
        double rate = FOT_RATE;
        double tolerance = FOT_TOLERANCE;
        double min = FOT_MIN;
        double max = FOT_MAX;
        double iCap = FOT_I_CAP;
//        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
//        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
//        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
//        double rate = SmartDashboard.getNumber("rate", 0.1);
//        double tolerance = SmartDashboard.getNumber("tolerance", 2);
//        double min = SmartDashboard.getNumber("min", 0.2);
//        double max = SmartDashboard.getNumber("max", 0.5);
        turnPID = new PID(p, i, d, min, max, rate, tolerance, iCap);

        if (Robot.getTalonMode() != driveTrain.getCurrentControlMode()) {
            driveTrain.setCurrentControlMode(Robot.getTalonMode());
        }
        driveTrain.setRampRate(0);
        driveTrain.setPID(DRIVE_P, DRIVE_I, DRIVE_D, DRIVE_F);

        arcadeAngle = driveTrain.getRobotYaw();
    }

    /**
     * Called repeatedly while the command is running.
     */
    protected void execute() {
        DriveTrain.DriveMode mode;
        double x;
        double y;
        double speed;
        double angle;

        double scale = 1 - OI.manager.getControllerAxis(LogitechF310.ControllerAxis.RightTrigger);
        if (scale < 0.2) scale = 0.2;
        OI.manager.setScale(scale);
        if (OI.manager.getButtonPressed(LogitechF310.Buttons.LB)) {
            mode = DriveTrain.DriveMode.FIELD_2_JOYSTICK;
        } else {
            mode = Robot.getDriveMode(); //Get drive mode from SmartDashboard
        }
        switch (mode) {
            case TANK:
                tankDrive(OI.manager.getLeftDriveY() * scale, OI.manager.getRightDriveY() * scale);
                break;
            case ARCADE_1_JOYSTICK:
                arcadeDrive(OI.manager.getRightDriveY() * scale, OI.manager.getRightDriveX() * scale);
                break;
            case ARCADE_2_JOYSTICK:
                arcadeDrive(OI.manager.getRightDriveY() * scale, OI.manager.getLeftDriveX() * scale);
                break;
            case FIELD_1_JOYSTICK:
                x = OI.manager.getRightDriveX();
                y = OI.manager.getRightDriveY();
                angle = Math.abs(x) >= Constants.JOYSTICK_DEADZONE || Math.abs(y) >= Constants.JOYSTICK_DEADZONE ? OI.manager.getRightDriveAngle() : 500;
                speed = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
                fieldOrientedDrive(angle, speed, 1);
                break;
            case FIELD_2_JOYSTICK:
                x = OI.manager.getLeftDriveX();
                y = OI.manager.getLeftDriveY();
                angle = Math.abs(x) >= Constants.JOYSTICK_DEADZONE || Math.abs(y) >= Constants.JOYSTICK_DEADZONE ? OI.manager.getLeftDriveAngle() : 500;
                speed = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
                fieldOrientedDrive(angle, OI.manager.getRightDriveY() * scale, speed);
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
     * @param moveSpeed The speed to drive at.
     */
    private void fieldOrientedDrive(double angle, double moveSpeed, double turnSpeed) {
        double output;
        if (moveSpeed > 1) moveSpeed = 1;
        if (turnSpeed > 1) turnSpeed = 1;
        if (angle < 360) {
            double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
            output = turnPID.compute(error);
        } else {
            output = 0;
        }
//        System.out.printf("error: %f, output: %f, angle: %f, speed: %f, l: %f, R: %f\n", AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle), output, angle, moveSpeed, moveSpeed / 2 - output * turnSpeed, moveSpeed / 2 + output * turnSpeed);
        driveTrain.setSpeedsPercent(moveSpeed / 2 - output * turnSpeed, moveSpeed / 2 + output * turnSpeed);
    }

    /**
     * Performs arcade driving
     * @param speed The magnitude of speed from -1 to 1
     * @param turning The turning magnitude from -1 to 1
     */
    public void arcadeDrive(double speed, double turning) {
        speed /= 2;
        turning /= 3;

        if (Math.abs(speed - lastSetpointSpeed) > ARCADE_RATE) {
            speed = speed < lastSetpointSpeed ? lastSetpointSpeed - ARCADE_RATE : lastSetpointSpeed + ARCADE_RATE;
        }
        if (Math.abs(turning - lastSetpointTurning) > Constants.DriveTrain.ARCADE_RATE) {
            turning = turning < lastSetpointTurning ? lastSetpointTurning - ARCADE_RATE : lastSetpointTurning + ARCADE_RATE;
        }
        lastSetpointSpeed = speed;
        lastSetpointTurning = turning;
        driveTrain.setSpeedsPercent(speed + turning, speed - turning);
    }


    /**
     * Takes to speed values from -1 to 1 and uses them for tank driving
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void tankDrive(double lSpeed, double rSpeed) {
        driveTrain.setSpeedsPercent(lSpeed, rSpeed);
    }
}
