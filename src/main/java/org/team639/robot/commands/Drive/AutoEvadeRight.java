package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoEvadeRight extends CommandGroup {
    public AutoEvadeRight() {
        super( "AutoEvadeRight");
        addSequential(new AutoTurnToRelativeAngle(-45, 1));
        addSequential(new AutoDriveForward(35, .3));
        addSequential(new AutoTurnToRelativeAngle(45, 1));
        addSequential(new AutoDriveForward(60, .3));
    }
}
