package org.team639.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team639.robot.commands.Gear.*;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 */
public class OI {

    private static Joystick leftDriveStick;
    private static Joystick rightDriveStick;
    private static Joystick controllerStick;

    private static Button acquisitionCycleTrigger;
    private static Button gearOpenButton;
    private static Button gearCloseButton;
    private static Button gearRaiseButton;
    private static Button gearLowerButton;

    /**
     * Initializes all of the Joysticks and button mappings.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        //Joysticks
        leftDriveStick = new Joystick(0);
        rightDriveStick = new Joystick(1);
        controllerStick = new Joystick(2);

        //Buttons
        acquisitionCycleTrigger = new JoystickButton(controllerStick, 1);
        gearOpenButton = new JoystickButton(controllerStick, 8);
        gearCloseButton = new JoystickButton(controllerStick, 9);
        gearLowerButton = new JoystickButton(controllerStick, 6);
        gearRaiseButton = new JoystickButton(controllerStick, 7);

        //Button mappings
        acquisitionCycleTrigger.whileHeld(new GearAcquisitionCycle());
        acquisitionCycleTrigger.whenReleased(new GearPickup());
        gearCloseButton.whenPressed(new GearClose());
        gearRaiseButton.whenPressed(new GearRaise());
        gearLowerButton.whenPressed(new GearLower());
        gearOpenButton.whenPressed(new GearOpen());

    }

    private OI() {
    }

    /**
     * Returns the left driver Joystick
     * @return the left driver Joystick
     */
    public static Joystick getLeftDriveStick() {
        return leftDriveStick;
    }

    /**
     * Returns the right driver Joystick
     * @return the right driver Joystick
     */
    public static Joystick getRightDriveStick() {
        return rightDriveStick;
    }

    /**
     * Returns the controller Joystick
     * @return The controller Joystick
     */
    public static Joystick getControllerStick() {
        return controllerStick;
    }
}
