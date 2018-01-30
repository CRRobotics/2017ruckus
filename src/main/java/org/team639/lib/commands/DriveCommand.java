package org.team639.lib.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command subclass for use with {@link DriveThread} via {@link ThreadedDriveCommand}.
 * @see ThreadedDriveCommand
 * @see DriveThread
 */
public abstract class DriveCommand extends Command {

    @Override
    protected abstract void initialize();

    @Override
    protected abstract void execute();

    @Override
    protected abstract void end();

    @Override
    protected abstract void interrupted();

    @Override
    protected abstract boolean isFinished();
}
