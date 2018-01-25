package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

public class ZeroYaw extends Command {

    public ZeroYaw() {
        super("ZeroYaw");
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
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return true;
    }
}
