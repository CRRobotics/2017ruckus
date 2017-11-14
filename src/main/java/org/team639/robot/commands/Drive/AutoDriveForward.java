package org.team639.robot.commands.Drive;

import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

/**
 * Command to autonomously drive forward a specified distance
 */
public class AutoDriveForward extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private double targetDistance;
    private int targetTicks;
    private int lStartingTicks;
    private int rStartingTicks;
    private double rSpeed;
    private double lSpeed;
    private double startSlow;
    private double lTickDiff;
    private double rTickDiff;


    public AutoDriveForward(double distance, double speed) {
        requires(driveTrain);

        if (distance < 0) speed *= -1;

        lSpeed = speed;
        rSpeed = speed;
        startSlow = Math.abs(Constants.DriveTrain.ENC_TICKS_PER_ROTATION * speed);

        targetDistance = distance;
        targetTicks = (int)(targetDistance * Constants.DriveTrain.TICKS_PER_INCH);
    }

    protected void initialize() {
        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(SmartMotorController.TalonControlMode.Speed);

        lStartingTicks = driveTrain.getLeftEncPos();
        rStartingTicks = driveTrain.getRightEncPos();
    }

    protected void execute() {
        lTickDiff = targetTicks + lStartingTicks - driveTrain.getLeftEncPos();
        rTickDiff = targetTicks - rStartingTicks + driveTrain.getRightEncPos();

        if (Math.abs(lTickDiff) <= startSlow || Math.abs(rTickDiff) <= startSlow) {

            double lMultiplier = Math.abs(lTickDiff) / (startSlow * 2);
            double rMultiplier = Math.abs(rTickDiff) / (startSlow * 2);
            if (lMultiplier > 1) lMultiplier = 1;
            if (rMultiplier > 1) rMultiplier = 1;
            if (lMultiplier < 0.2) lMultiplier = 0.2;
            if (rMultiplier < 0.2) rMultiplier = 0.2;

            driveTrain.setSpeedsPercent(lSpeed * lMultiplier, rSpeed * rMultiplier);
        } else {
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }
    }

    @Override
    protected boolean isFinished() {
        boolean left = (Math.abs(lTickDiff) < Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE);
        boolean right = (Math.abs(rTickDiff) < Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE);

        return left || right;
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