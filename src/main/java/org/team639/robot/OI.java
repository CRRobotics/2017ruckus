package org.team639.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.commands.Drive.AutoDriveForward;
import org.team639.robot.commands.Drive.AutoTurnToAngle;
import org.team639.robot.commands.Gear.*;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 */
public class OI {

    public static final JoystickManager manager = new LogitechF310(0);

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {

        //Button mappings
        manager.mapButton(LogitechF310.Buttons.RB, new GearAcquisitionCycle(), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.RB, new GearPickup(), JoystickManager.MappingType.WhenReleased);
//        manager.mapButton(LogitechF310.Buttons.B, new AutoDriveForward(18, 0.3), JoystickManager.MappingType.WhenPressed);
//        manager.mapButton(LogitechF310.Buttons.X, new AutoDriveForward(32, 0.3), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.A, new AutoTurnToAngle(0), JoystickManager.MappingType.WhenPressed);

    }

    private OI() {
    }
}
