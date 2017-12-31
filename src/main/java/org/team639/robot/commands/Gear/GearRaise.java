package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.GearAcquisition;

/**
 * Command that raises the claw
 */
public class GearRaise extends Command {
    private GearAcquisition gearAcquisition = Robot.getGearAcquisition();


    public GearRaise() {
        super("GearRaise");
        requires(gearAcquisition);
    }

    protected void initialize() {
        gearAcquisition.raise();
    }

    protected boolean isFinished() {
        return true;
    }
}