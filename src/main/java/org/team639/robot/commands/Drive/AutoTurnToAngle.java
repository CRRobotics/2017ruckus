package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.Auto.*;
import static org.team639.robot.Constants.DriveTrain.MIN_DRIVE_PERCENT;

/**
 * Turns the robot to a specified angle.
 * Constants for this routine are in the static Constants.Auto class and are prefixed with TTA_
 */
public class AutoTurnToAngle extends Command {

    private DriveTrain driveTrain;

    private double angle;
    private boolean done;

    private double lSpeed;
    private double rSpeed;

    private double startSlow;
    private double minSpeed;

    private PID pid;

    public AutoTurnToAngle(double pAngle, double speed) {
        super("AutoTurnToAngle");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        this.angle = pAngle % 360;

        speed = Math.abs(speed);
        int direction = AngleMath.shortestDirection(driveTrain.getRobotYaw(), pAngle);
        lSpeed = -1 * speed * direction;
        rSpeed = speed * direction;

        startSlow = 20 * speed; // TODO: This needs to be adjusted
        minSpeed = MIN_DRIVE_PERCENT / speed;
    }

    //anthony is a pretty cool guy
    protected void initialize() {
//        done = false;
//        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
//        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
//        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
//        double rate = SmartDashboard.getNumber("rate", 0.1);
//        double tolerance = TTA_TOLERANCE; // SmartDashboard.getNumber("tolerance", 2);
//        double min = SmartDashboard.getNumber("min", 0.2);
//        double max = SmartDashboard.getNumber("max", 0.5);
//        double iCap = SmartDashboard.getNumber("iCap", 0.2);
//        pid = new PID(TTA_P, TTA_I, TTA_D, TTA_MIN, TTA_MAX, TTA_RATE, TTA_TOLERANCE, TTA_I_CAP);

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);
        driveTrain.setRampRate(1);

    }

    protected void execute() {
        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
        SmartDashboard.putNumber("angle error", error);
        if (Math.abs(error) < startSlow) {
            System.out.println("slowing down");
            double multiplier = Math.abs(error) / (startSlow * 2); // TODO: Should this be multiplied by 2?
            if (multiplier > 1) multiplier = 1;
            if (multiplier < minSpeed) multiplier = minSpeed;
            driveTrain.setSpeedsPercent(lSpeed * multiplier, rSpeed * multiplier);
        } else {
            System.out.println("full speed");
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }
//        double val = pid.compute(error);
//        done = (val == 0);
//        driveTrain.setSpeedsPercent(-1 * val, val);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle)) < TTA_TOLERANCE;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {
        driveTrain.setSpeedsPercent(0, 0);
    }
}
