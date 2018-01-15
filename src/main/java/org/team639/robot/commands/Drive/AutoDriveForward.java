package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.DriveTrain.*;

/**
 * Command to autonomously drive forward a specified distance
 */
public class AutoDriveForward extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private boolean done = false;

    private double targetDistance;
    private int targetTicks;
    private double rSpeed;
    private double lSpeed;
    private double startSlow;
    private double lTickDiff;
    private double rTickDiff;
    private double targetLeft;
    private double targetRight;
    private double minSpeed;

    private double angle;

    private PID pid;
    private PID turnPID;

    public AutoDriveForward(double distance, double speed) {
        super("AutoDriveForward");
        requires(driveTrain);

        if (distance < 0) speed *= -1;

        lSpeed = speed;
        rSpeed = speed;
        startSlow = Math.abs(Constants.DriveTrain.ENC_TICKS_PER_ROTATION * speed); // TODO: This value may need to be changed.
        minSpeed = speed / MIN_DRIVE_PERCENT;

        targetDistance = distance;
        targetTicks = (int)(targetDistance * Constants.DriveTrain.TICKS_PER_INCH);

        pid = new PID(0,0,0,0,0,0,0, 0.2); //placeholder should remove
    }

    protected void initialize() {
        done = false;

        angle = driveTrain.getRobotYaw();

        targetLeft = driveTrain.getLeftEncPos() + targetTicks;
        targetRight = driveTrain.getRightEncPos() + targetTicks;

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);
        driveTrain.setRampRate(1); // TODO: Maybe change this.

//        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
//        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
//        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
//        double rate = SmartDashboard.getNumber("rate", 0.1);
//        double tolerance = SmartDashboard.getNumber("tolerance", 200);
//        double min = SmartDashboard.getNumber("min", 0.2);
//        double max = SmartDashboard.getNumber("max", 0.5);
//        pid = new PID(p, i, d, min, max, rate, tolerance, 0.2);
//
//        turnPID = new PID(FOT_P, FOT_I, FOT_D, FOT_MIN, FOT_MAX, FOT_RATE, FOT_TOLERANCE, FOT_I_CAP);
    }

    protected void execute() {
        lTickDiff = Math.abs(targetLeft - driveTrain.getLeftEncPos());
        rTickDiff = Math.abs(targetRight - driveTrain.getRightEncPos());

//        double val = pid.compute(lTickDiff);
//        System.out.println(lTickDiff + ", " + val);
//
//        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
//        double output = turnPID.compute(error);
////        System.out.println((val - output) + ", " + (val + output));
//        driveTrain.setSpeedsPercent(val - output, val + output);
//        done = (val == 0);

        if (Math.abs(lTickDiff) <= startSlow) {

            double multiplier = Math.abs(lTickDiff) / (startSlow * 2); // TODO: Should this be multiplied by 2?
//            double rMultiplier = Math.abs(rTickDiff) / (startSlow * 2);
            if (multiplier > 1) multiplier = 1;
//            if (rMultiplier > 1) rMultiplier = 1;
            if (multiplier < minSpeed) multiplier = minSpeed;
//            if (rMultiplier < 0.2) rMultiplier = 0.2;

            driveTrain.setSpeedsPercent(lSpeed * multiplier, rSpeed * multiplier);
        } else {
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }
    }

    @Override
    protected boolean isFinished() {
        boolean left = (Math.abs(lTickDiff) < SmartDashboard.getNumber("tolerance", Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE));
        boolean right = (Math.abs(rTickDiff) < SmartDashboard.getNumber("tolerance", Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE));
//        if (done) System.out.println(done);
        return left;
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
