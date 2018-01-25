package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.sensor.distance.DistanceSensor;
import org.team639.robot.Robot;
import org.team639.robot.RobotMap;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.DriveTrain.MIN_DRIVE_PERCENT;

public class Approach extends Command {
    private DriveTrain driveTrain;

    private boolean done;
    private DistanceSensor sonar;
    private double lSpeed;
    private double rSpeed;
    private double gSpeed;
    private double tolerance;
    private double distance;
    private double slow;


    public Approach(double pdistance, double speed) {
        super("Approach");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        sonar = RobotMap.getFrontSonar();
        gSpeed = Math.abs(speed);
        lSpeed = gSpeed;
        rSpeed = gSpeed;
        distance = pdistance;
        tolerance = 1;
        distance = pdistance < tolerance ? tolerance*2 : distance;
        slow = 12;


    }

    protected void initialize() {
        done = gSpeed < MIN_DRIVE_PERCENT;
        if(!done)
            done = sonar.getDistanceInches() <= distance + tolerance;

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

    }

    protected void execute() {
        if(sonar.getDistanceInches() > distance + slow)
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
//        account for MIN_DRIVE_PERCENT
//        else if(sonar.getDistanceInches() > distance + tolerance) {
//            double multiplier = (sonar.getDistanceInches() - (distance + tolerance))/slow;
//            lSpeed = (gSpeed - MIN_DRIVE_PERCENT)*multiplier + MIN_DRIVE_PERCENT;
//            rSpeed = (gSpeed - MIN_DRIVE_PERCENT)*multiplier + MIN_DRIVE_PERCENT;
//            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
//        }
        else if(sonar.getDistanceInches() > distance + tolerance) {
            double multiplier = (sonar.getDistanceInches() - (distance + tolerance))/slow;
            lSpeed = gSpeed * multiplier;
            rSpeed = gSpeed * multiplier;
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }

        done = gSpeed < MIN_DRIVE_PERCENT;
        if(!done)
            done = sonar.getDistanceInches() <= distance + tolerance;

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
