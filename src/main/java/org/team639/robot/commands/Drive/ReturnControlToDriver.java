package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

/**
 * A command that interrupts all other drive commands, returning control to the human driver.
 * Created by Jack Greenberg <theProgrammerJack@gmail.com> on 1/24/2018.
 */
public class ReturnControlToDriver extends Command {

    public ReturnControlToDriver() {
        super("ReturnControlToDriver");
        requires(Robot.getDriveTrain());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
