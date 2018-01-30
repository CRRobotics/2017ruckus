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
 * Drives forward until certain distance is sensed.
 */
public class DriveToDistanceAwayFront extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private boolean done = false;

    private double targetDistance;

    private double angle;

    private PID pid;
    private PID turnPID;

    public DriveToDistanceAwayFront(double distance) {
        super("DriveToDistanceAwayFront");
        requires(driveTrain);

        targetDistance = distance;
    }

    protected void initialize() {
        done = false;

        angle = driveTrain.getRobotYaw();

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
        double rate = SmartDashboard.getNumber("rate", 0.1);
        double tolerance = SmartDashboard.getNumber("tolerance", 200);
        double min = SmartDashboard.getNumber("min", 0.2);
        double max = SmartDashboard.getNumber("max", 0.5);
        pid = new PID(p, i, d, min, max, rate, tolerance, 0.2);
//
        turnPID = new PID(FOT_P, FOT_I, FOT_D, FOT_MIN, FOT_MAX, FOT_RATE, FOT_TOLERANCE, FOT_I_CAP);
    }

    protected void execute() {
        SmartDashboard.putBoolean("running", true);

        double diff = driveTrain.getFrontDistance() - targetDistance;
        SmartDashboard.putNumber("diff", diff);
        double val = pid.compute(diff);
//
//        double angleError = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
//        double output = turnPID.compute(angleError);
        driveTrain.setSpeedsPercent(val /*- output*/, val /*+ output*/);
        done = (val == 0);
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {
        SmartDashboard.putBoolean("running", false);
        driveTrain.setSpeedsPercent(0, 0);
    }
}
