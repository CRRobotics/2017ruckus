package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.GearAcquisition;

/**
 * Command to wait until a gear is detected by the sensor
 */
public class WaitForGear extends Command {
    private GearAcquisition gearAcquisition = Robot.getGearAcquisition();

    public WaitForGear() {
        super("WaitForGear");
        requires(gearAcquisition);
    }

    @Override
    protected boolean isFinished() {
        return gearAcquisition.gearDetected();
    }
}
