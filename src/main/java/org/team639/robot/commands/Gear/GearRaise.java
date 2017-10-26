package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

public class GearRaise extends Command {
    public GearRaise() {
        requires(Robot.gearAcquisition);
    }

    protected void initialize() {
        Robot.gearAcquisition.raise();
    }

    protected boolean isFinished() {
        return true;
    }
}