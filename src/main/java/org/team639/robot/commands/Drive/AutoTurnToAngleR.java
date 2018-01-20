package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.math.AngleMath;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;


/**
 * Turns the robot to a specified angle.
 * Constants for this routine are in the static Constants.Auto class and are prefixed with TTA_
 */
public class AutoTurnToAngleR extends Command {

    private DriveTrain driveTrain;

    private double angle;
    private boolean done;

    private double lSpeed;
    private double rSpeed;
    private double maxTolerance;
    private double minTolerance;


    public AutoTurnToAngleR(double pAngle, double speed) {
        super("AutoTurnToAngle");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        angle = pAngle % 360;

        speed = Math.abs(speed);
        lSpeed = -1 * speed;
        rSpeed = speed;
        maxTolerance = 15;
        minTolerance = 5;

    }

    protected void initialize() {
        done = Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle)) <= minTolerance;


        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

    }

    protected void execute() {
            if(Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle)) > maxTolerance) {
                lSpeed *= AngleMath.shortestDirection(driveTrain.getRobotYaw(), angle);
                rSpeed *= AngleMath.shortestDirection(driveTrain.getRobotYaw(), angle);
                driveTrain.setSpeedsPercent(lSpeed, rSpeed);
            }
            else if(Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle)) > minTolerance) {
                double multiplier = Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle)) - minTolerance;
                multiplier /= maxTolerance;
                lSpeed *= AngleMath.shortestDirection(driveTrain.getRobotYaw(), angle) * multiplier;
                rSpeed *= AngleMath.shortestDirection(driveTrain.getRobotYaw(), angle) * multiplier;
                driveTrain.setSpeedsPercent(lSpeed, rSpeed);
            }
//            else
//                driveTrain.setSpeedsPercent(0, 0);
//        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);

        done = Math.abs(AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle)) <= minTolerance;

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
