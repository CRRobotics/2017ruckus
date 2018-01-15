package org.team639.robot;

import org.team639.lib.controls.DoubleLogitechAttack3;
import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.commands.Drive.AutoDriveForward;
import org.team639.robot.commands.Drive.AutoEvadeLeft;
import org.team639.robot.commands.Drive.AutoTurnToAngle;
import org.team639.robot.commands.Drive.ZeroYaw;
import org.team639.robot.commands.Gear.*;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 */
public class OI {

    public static final JoystickManager manager = new LogitechF310(0); // new DoubleLogitechAttack3(); // new LogitechF310(0);

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {
        //Button mappings
//        manager.mapButton(LogitechF310.Buttons.RB, new GearAcquisitionCycle(), JoystickManager.MappingType.WhenPressed);
//        manager.mapButton(LogitechF310.Buttons.RB, new GearPickup(), JoystickManager.MappingType.WhenReleased);
        manager.mapButton(LogitechF310.Buttons.B, new ZeroYaw(), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.X, new AutoEvadeLeft(), JoystickManager.MappingType.WhenPressed);
//        manager.mapButton(LogitechF310.Buttons.A, new AutoTurnToAngle(0), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVUp, new AutoTurnToAngle(90, 1), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVRight, new AutoTurnToAngle(0, 1), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVDown, new AutoTurnToAngle(270, 1), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVLeft, new AutoTurnToAngle(180, 1), JoystickManager.MappingType.WhenPressed);
    //    manager.mapButton(LogitechF310.Buttons.B)

    }

    private OI() {
    }
}
