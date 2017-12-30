package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.GearAcquisition;

/**
 * Command the raises the claw
 */
public class GearLower extends Command {
    private GearAcquisition gearAcquisition = Robot.getGearAcquisition();


    public GearLower() {
        super("GearLower");
        requires(gearAcquisition);
    }

    protected void initialize() {
        gearAcquisition.lower();
    }

    protected boolean isFinished() {
        return true;
    }
}