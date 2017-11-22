package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

/**
 * Command to autonomously drive forward a specified distance
 */
public class DriveToTarget extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private double targetDistance;
    private double targetAngle;
    public enum DTTStates {
        _start,
        _waitForAngle,
        _waitForMove,
        _finish
    }
    private DTTStates CurrentState;

    public DriveToTarget() {
        requires(driveTrain);
    }

    protected void initialize() {
        CurrentState = DTTStates._finish;
        targetDistance = Robot.visionTable.getNumber("Distance");
        targetAngle = Robot.visionTable.getNumber("XAngleToTarget");
        System.out.print("Angle: " + targetAngle );
        System.out.println(" ,Distance: " + targetDistance );
        if((targetDistance > 10)&&(targetDistance < 100)&&(targetAngle < 20)) {
            CurrentState = DTTStates._start;
        }
    }

    protected void execute() {
        switch(CurrentState){
            case _start:
                driveTrain.DriveToAngle(targetAngle);
                CurrentState = DTTStates._waitForAngle;
                break;
            case _waitForAngle:
                if (driveTrain.isDoneDriveToAngle()){
                    driveTrain.DriveToDistance(targetDistance-10);
                    CurrentState = DTTStates._waitForMove;
                }
                break;
            case _waitForMove:
                if (driveTrain.isDoneDriveToDistance()){
                    CurrentState = DTTStates._finish;
                }
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return CurrentState == DTTStates._finish;
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