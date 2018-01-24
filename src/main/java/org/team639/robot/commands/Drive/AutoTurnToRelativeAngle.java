package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.Auto.*;
import static org.team639.robot.Constants.DriveTrain.MIN_DRIVE_PERCENT;

/**
 * Turns the robot to a specified angle.
 * Constants for this routine are in the static Constants.Auto class and are prefixed with TTA_
 */
public class AutoTurnToRelativeAngle extends Command {

    private DriveTrain driveTrain;

    private double relAngle;
    private double angle;
    private boolean done;

    private double lSpeed;
    private double rSpeed;

    private double startSlow;
    private double minSpeed;

    private PID pid;

    public AutoTurnToRelativeAngle(double angle, double speed) {
        super("AutoTurnToRelativeAngle");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        this.relAngle = angle % 360;
    }

    protected void initialize() {
        done = false;
        this.angle = driveTrain.getRobotYaw() + relAngle;
        this.angle %= 360;
        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);
        pid = new PID(TTA_P, TTA_I, TTA_D, TTA_MIN, TTA_MAX, TTA_RATE, TTA_TOLERANCE, TTA_I_CAP);
    }

    protected void execute() {
        double error = Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle));
        double val = pid.compute(error);
        done = (val == 0);
        driveTrain.setSpeedsPercent(-1 * val, val);
    }

    @Override
    protected boolean isFinished() {
        return AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle) < TTA_TOLERANCE;
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
