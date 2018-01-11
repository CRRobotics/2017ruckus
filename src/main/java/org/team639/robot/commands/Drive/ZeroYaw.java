package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

public class ZeroYaw extends Command {

    public ZeroYaw() {
        requires(Robot.getDriveTrain());
    }

    @Override
    protected void initialize() {
        Robot.getDriveTrain().zeroRobotYaw();
    }

    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     * <p>
     * <p>Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. We recommend using {@link InstantCommand}
     * for this.
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return true;
    }
}
