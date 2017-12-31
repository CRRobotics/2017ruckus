package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.robot.Constants;

/**
 * Coordinates closing and raising the claw to acquire a gear
 */
public class GearPickup extends CommandGroup {
    public GearPickup() {
        super("GearPickup");
        addSequential(new GearClose());
        addSequential(new WaitCommand(Constants.CLOSE_CLAW_TIME));
        addSequential(new GearRaise());
    }
}
