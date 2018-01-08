package org.team639.lib.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.subsystem.DriveSubsystem;

/**
 * Created by Jack Greenberg <theProgrammerJack@gmail.com> on 1/7/2018.
 * Part of 2017ruckus.
 */
public class ThreadedDriveCommand extends Command {
    private DriveCommand command;
    private volatile boolean finished;

    public ThreadedDriveCommand(DriveCommand command, DriveSubsystem drive) {
        this.command = command;
        requires(drive);
    }

    protected void initialize() {
        finished = false;
        DriveThread.getInstance().runCommand(command, this);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void interrupted() {
        DriveThread.getInstance().interruptCommand();
    }

    void done() {
        finished = true;
    }
}

