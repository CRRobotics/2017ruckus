package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

/**
 * Command that closes the claw
 */
public class GearClose extends Command {
    public GearClose() {
        requires(Robot.gearAcquisition);
    }

    protected void initialize() {
        Robot.gearAcquisition.close();
    }

    protected boolean isFinished() {
        return true;
    }
}