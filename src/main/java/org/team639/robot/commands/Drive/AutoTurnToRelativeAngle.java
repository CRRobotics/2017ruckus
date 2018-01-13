package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

public class AutoTurnToRelativeAngle extends Command {
    int angle;
    public AutoTurnToRelativeAngle(int angle){
        this.angle = angle;
    }


    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        new AutoTurnToAngle(Robot.getDriveTrain().getRobotYaw() + angle).start();
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
