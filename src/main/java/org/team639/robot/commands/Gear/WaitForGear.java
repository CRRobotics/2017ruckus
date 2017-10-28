package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

/**
 * Command to wait until a gear is detected by the sensor
 */
public class WaitForGear extends Command {

    public WaitForGear() {
        requires(Robot.gearAcquisition);
    }

    @Override
    protected boolean isFinished() {
        return Robot.gearAcquisition.gearDetected();
    }
}
