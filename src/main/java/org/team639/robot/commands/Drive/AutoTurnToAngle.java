package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.Auto.*;

/**
 * Turns the robot to a specified angle.
 * Constants for this routine are in the static Constants.Auto class and are prefixed with TTA_
 */
public class AutoTurnToAngle extends Command {

    private DriveTrain driveTrain;

    private double angle;
    private boolean done;

    private PID pid;

    public AutoTurnToAngle(double angle) {
        super("AutoTurnToAngle");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        this.angle = angle % 360;
    }

    protected void initialize() {
        done = false;
//        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
//        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
//        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
//        double rate = SmartDashboard.getNumber("rate", 0.1);
//        double tolerance = SmartDashboard.getNumber("tolerance", 200);
//        double min = SmartDashboard.getNumber("min", 0.2);
//        double max = SmartDashboard.getNumber("max", 0.5);
//        double iCap = SmartDashboard.getNumber("iCap", 0.2);
        pid = new PID(TTA_P, TTA_I, TTA_D, TTA_MIN, TTA_MAX, TTA_RATE, TTA_TOLERANCE, TTA_I_CAP);

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

    }

    protected void execute() {
        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
        double val = pid.compute(error);
        done = (val == 0);
        driveTrain.setSpeedsPercent(-1 * val, val);
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
        driveTrain.setSpeedsPercent(0, 0);
    }
}
