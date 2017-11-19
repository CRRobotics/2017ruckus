package org.team639.robot.commands.Drive;

import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

/**
 * Command to autonomously drive forward a specified distance
 */
public class AutoDriveToPosition extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private double targetDistance;

    public AutoDriveToPosition(double distance) {
        requires(driveTrain);
       targetDistance = distance;
    }

    protected void initialize() {
        driveTrain.DriveToDistance(targetDistance);
    }
/*
    protected void execute() {

    }
*/
    @Override
    protected boolean isFinished() {
/*        int lVelocity = driveTrain.getLeftEncVelocity();
        int rVelocity = driveTrain.getRightEncVelocity();
        System.out.print(lVelocity + ", ");
        System.out.println(rVelocity);
        boolean left = (Math.abs(driveTrain.getlFinalPosition() - driveTrain.getLeftEncPos()) < Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE);
        boolean right = (Math.abs(driveTrain.getrFinalPosition() - driveTrain.getRightEncPos()) < Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE);
*/
        return driveTrain.isDoneDriveToDistance();
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
