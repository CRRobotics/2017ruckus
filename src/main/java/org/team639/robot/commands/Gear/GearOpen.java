package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

public class GearOpen extends Command {
    public GearOpen() {
        requires(Robot.gearAcquisition);
    }

    protected void initialize() {
        Robot.gearAcquisition.open();
    }
    protected boolean isFinished() {
        return true;
    }

}
