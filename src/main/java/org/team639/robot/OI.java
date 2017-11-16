package org.team639.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.commands.Drive.*;
import org.team639.robot.commands.Gear.*;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 */
public class OI {

//    private static Joystick leftDriveStick = new Joystick(0);
//    private static Joystick rightDriveStick = new Joystick(1);
//    private static Joystick controllerStick = new Joystick(2);
//    private static Joystick theRadioStick = new Joystick(0);

    public static final JoystickManager manager = new LogitechF310(0);

//    private static Button acquisitionCycleTrigger;
//    private static Button autoDriveForward;
//    private static Button gearOpenButton;
//    private static Button gearCloseButton;
//    private static Button gearRaiseButton;
//    private static Button gearLowerButton;

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {
        //Joysticks
//        leftDriveStick = new Joystick(0);
//        rightDriveStick = new Joystick(1);
//        controllerStick = new Joystick(2);

        //Buttons
//        acquisitionCycleTrigger = new JoystickButton(theRadioStick, 6);
//        autoDriveForward = new JoystickButton(theRadioStick, 2);
//        gearOpenButton = new JoystickButton(controllerStick, 8);
//        gearCloseButton = new JoystickButton(controllerStick, 9);
//        gearLowerButton = new JoystickButton(controllerStick, 6);
//        gearRaiseButton = new JoystickButton(controllerStick, 7);

        //Button mappings
//        acquisitionCycleTrigger.whenPressed(new GearAcquisitionCycle());
//        acquisitionCycleTrigger.whenReleased(new GearPickup());
//        autoDriveForward.whenPressed(new AutoDriveForward(48, 0.5));
        manager.mapButton(LogitechF310.Buttons.RB, new GearAcquisitionCycle(), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.RB, new GearPickup(), JoystickManager.MappingType.WhenReleased);
        manager.mapButton(LogitechF310.Buttons.B, new AutoDriveToPosition(12), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.X, new AutoDriveToPosition(-12), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.Y, new AutoTurnByAngle(90), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.A, new AutoTurnByAngle(-90), JoystickManager.MappingType.WhenPressed);
//        gearCloseButton.whenPressed(new GearClose());
//        gearRaiseButton.whenPressed(new GearRaise());
//        gearLowerButton.whenPressed(new GearLower());
//        gearOpenButton.whenPressed(new GearOpen());

    }

    private OI() {
    }

    /**
     * Returns the left driver Joystick
     * @return the left driver Joystick
     */
//    public static Joystick getLeftDriveStick() {
//        return leftDriveStick;
//    }
//
//    /**
//     * Returns the right driver Joystick
//     * @return the right driver Joystick
//     */
//    public static Joystick getRightDriveStick() {
//        return rightDriveStick;
//    }
//
//    /**
//     * Returns the controller Joystick
//     * @return The controller Joystick
//     */
//    public static Joystick getControllerStick() {
//        return controllerStick;
//    }

//    public static Joystick getStick() {
//        return theRadioStick;
//    }
}
