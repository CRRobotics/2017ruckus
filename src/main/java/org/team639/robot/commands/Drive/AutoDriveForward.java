package org.team639.robot.commands.Drive;

import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.PID;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

/**
 * Command to autonomously drive forward a specified distance
 */
public class AutoDriveForward extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private boolean done = false;

    private double targetDistance;
    private int targetTicks;
    private int lStartingTicks;
    private int rStartingTicks;
    private double rSpeed;
    private double lSpeed;
    private double startSlow;
    private double lTickDiff;
    private double rTickDiff;

    private PID pid;

    public AutoDriveForward(double distance, double speed) {
        requires(driveTrain);

        if (distance < 0) speed *= -1;

        lSpeed = speed;
        rSpeed = speed;
        startSlow = Math.abs(Constants.DriveTrain.ENC_TICKS_PER_ROTATION * speed);

        targetDistance = distance;
        targetTicks = (int)(targetDistance * Constants.DriveTrain.TICKS_PER_INCH);

        pid = new PID(0,0,0,0,0,0,0, 0.2); //placeholder should remove
    }

    protected void initialize() {
        done = false;

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(SmartMotorController.TalonControlMode.Speed);

        lStartingTicks = driveTrain.getLeftEncPos();
        rStartingTicks = driveTrain.getRightEncPos();
        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
        double rate = SmartDashboard.getNumber("rate", 0.1);
        double tolerance = SmartDashboard.getNumber("tolerance", 200);
        double min = SmartDashboard.getNumber("min", 0.2);
        double max = SmartDashboard.getNumber("max", 0.5);
        pid = new PID(p, i, d, min, max, rate, tolerance, 0.2);
    }

    protected void execute() {
        lTickDiff = targetTicks + lStartingTicks - driveTrain.getLeftEncPos();
        rTickDiff = targetTicks + rStartingTicks - driveTrain.getRightEncPos();

        double val = pid.compute(lTickDiff);
//        System.out.println(val);
        driveTrain.setSpeedsPercent(val, val);
        done = (val == 0);

//        if (Math.abs(lTickDiff) <= startSlow || Math.abs(rTickDiff) <= startSlow) {
//
//            double lMultiplier = Math.abs(lTickDiff) / (startSlow * 2);
//            double rMultiplier = Math.abs(rTickDiff) / (startSlow * 2);
//            if (lMultiplier > 1) lMultiplier = 1;
//            if (rMultiplier > 1) rMultiplier = 1;
//            if (lMultiplier < 0.2) lMultiplier = 0.2;
//            if (rMultiplier < 0.2) rMultiplier = 0.2;
//
//            driveTrain.setSpeedsPercent(lSpeed * lMultiplier, rSpeed * rMultiplier);
//        } else {
//            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
//        }
    }

    @Override
    protected boolean isFinished() {
        boolean left = (Math.abs(lTickDiff) < SmartDashboard.getNumber("tolerance", Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE));
        boolean right = (Math.abs(rTickDiff) < SmartDashboard.getNumber("tolerance", Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE));
//        if (done) System.out.println(done);
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
