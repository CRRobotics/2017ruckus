package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.robot.Constants;

/**
 * Manages picking up gears automatically
 */
public class GearAcquisitionCycle extends CommandGroup {
     public GearAcquisitionCycle() {
         super("GearAcquisitionCycle");
         addSequential(new GearOpen());
         addSequential(new WaitCommand(Constants.OPEN_CLAW_TIME));
         addSequential(new GearLower());
         addSequential(new WaitForGear());
         addSequential(new GearPickup());
     }
}
