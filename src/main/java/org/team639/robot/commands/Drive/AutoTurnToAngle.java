package org.team639.robot.commands.Drive;

import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

public class AutoTurnToAngle extends Command {

    private DriveTrain driveTrain;

    private double angle;
    private int direction;
    private boolean done;

    private PID pid;

    public AutoTurnToAngle(double angle) {
        super("AutoTurnToAngle");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        this.angle = angle % 360;
        direction = AngleMath.shortestDirection(driveTrain.getRobotYaw(), angle);
    }

    protected void initialize() {
        done = false;
        System.out.println("hey there");
        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.DRIVE_P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.DRIVE_I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.DRIVE_I);
        double rate = SmartDashboard.getNumber("rate", 0.1);
        double tolerance = SmartDashboard.getNumber("tolerance", 200);
        double min = SmartDashboard.getNumber("min", 0.2);
        double max = SmartDashboard.getNumber("max", 0.5);
        double iCap = SmartDashboard.getNumber("iCap", 0.2);
        pid = new PID(p, i, d, min, max, rate, tolerance, iCap);

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(SmartMotorController.TalonControlMode.Speed);

    }

    protected void execute() {
        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
//        System.out.println(error);
        double val = pid.compute(error);
//        System.out.println(val);
        done = (val == 0);
//        done = true;
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
