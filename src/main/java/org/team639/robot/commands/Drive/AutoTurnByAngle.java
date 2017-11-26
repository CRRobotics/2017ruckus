package org.team639.robot.commands.Drive;

import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

/**
 * Command to autonomously turn a specified angle
 */
public class AutoTurnByAngle extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private double targetDistance;
    private int targetTicks;
    private int lStartingTicks;
    private int rStartingTicks;
    private double rSpeed;
    private double lSpeed;
    private double lTickDiff;
    private double rTickDiff;

    public AutoTurnByAngle(double angle) {
        requires(driveTrain);

        // 28" between wheels so a circumference of 88" or .244" per degree
        targetDistance = angle * .244;
        targetTicks = (int)(targetDistance * Constants.DriveTrain.TICKS_PER_INCH);

        // 28" between wheels so a circumference of 88" or .244" per degree
    }

    protected void initialize() {
        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(SmartMotorController.TalonControlMode.MotionMagic);

        lStartingTicks = driveTrain.getLeftEncPos();
        rStartingTicks = driveTrain.getRightEncPos();
        System.out.println("lStartingTicks: " + lStartingTicks);
        System.out.println("rStartingTicks: " + rStartingTicks);
        System.out.println("targetTicks: " + targetTicks);

        driveTrain.setSpeedsRaw((lStartingTicks - targetTicks) , (rStartingTicks - targetTicks) * -1 );
    }

    protected void execute() {

        lTickDiff = (lStartingTicks - targetTicks - driveTrain.getLeftEncPos());
        rTickDiff = (rStartingTicks - targetTicks - driveTrain.getRightEncPos());

        System.out.print(lTickDiff + ", ");
        System.out.println(rTickDiff);

    }

    @Override
    protected boolean isFinished() {
        boolean left = (Math.abs(lTickDiff) < Constants.DriveTrain.TURN_TOLERANCE);
        boolean right = (Math.abs(rTickDiff) < Constants.DriveTrain.TURN_TOLERANCE);

        return left||right;
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
