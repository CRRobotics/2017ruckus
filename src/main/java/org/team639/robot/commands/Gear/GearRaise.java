package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.RobotMap;

public class GearRaise extends Command {
    protected void initialize() {
        Robot.gearAcquisition.raise();
    }

    protected boolean isFinished() {
        return true;
    }
}