package org.team639.lib.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Jack Greenberg <theProgrammerJack@gmail.com> on 1/7/2018.
 * Part of 2017ruckus.
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
