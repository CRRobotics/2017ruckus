package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.GearAcquisition;

/**
 * Command that opens the claw
 */
public class GearOpen extends Command {
    private GearAcquisition gearAcquisition = Robot.getGearAcquisition();


    public GearOpen() {
        requires(gearAcquisition);
    }

    protected void initialize() {
        gearAcquisition.open();
    }
    protected boolean isFinished() {
        return true;
    }

}
