package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.GearAcquisition;

/**
 * Command that closes the claw
 */
public class GearClose extends Command {
    private GearAcquisition gearAcquisition = Robot.getGearAcquisition();


    public GearClose() {
        requires(gearAcquisition);
    }

    protected void initialize() {
        gearAcquisition.close();
    }

    protected boolean isFinished() {
        return true;
    }
}