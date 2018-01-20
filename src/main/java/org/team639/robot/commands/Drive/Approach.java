package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.sensor.distance.MaxSonarEZ4Analog;
import org.team639.robot.Robot;
import org.team639.robot.RobotMap;
import org.team639.robot.subsystems.DriveTrain;

public class Approach extends Command {
    private DriveTrain driveTrain;

    private boolean done;
    private MaxSonarEZ4Analog sonar;
    private double lSpeed;
    private double rSpeed;
    private double tolerance;
    private double slow;


    public Approach(double distance, double speed) {
        super("AutoTurnToAngle");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        sonar = RobotMap.getSonar();
        speed = Math.abs(speed);
        lSpeed = speed;
        rSpeed = speed;
        tolerance = 1;
        tolerance = distance < tolerance ? distance : tolerance;
        slow = tolerance * 2;


    }

    protected void initialize() {
        done = sonar.getDistanceInches() <= tolerance;
        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

    }

    protected void execute() {
        if(sonar.getDistanceInches() > slow)
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        else if(sonar.getDistanceInches() > tolerance) {
            double multiplier = sonar.getDistanceInches() - tolerance;
            multiplier /= slow;
            lSpeed *= multiplier;
            rSpeed *= multiplier;
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }

        done = sonar.getDistanceInches() <= tolerance;

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
