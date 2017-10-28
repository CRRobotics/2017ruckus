package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

/**
 * Command the raises the claw
 */
public class GearLower extends Command {
    public GearLower() {
        requires(Robot.gearAcquisition);
    }

    protected void initialize() {
        Robot.gearAcquisition.lower();
    }

    protected boolean isFinished() {
        return true;
    }
}