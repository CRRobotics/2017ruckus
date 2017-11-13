package org.team639.lib.controls;

import edu.wpi.first.wpilibj.command.Command;

public abstract class JoystickManager {

    public enum MappingType {
        WhenPressed,
        WhileHeld,
        WhenReleased,
        ToggleWhenPressed,
        CancelWhenPressed
    }

    public interface ButtonType {}

    /**
     * Returns the Y axis value of the left drive Joystick with forward being 1 and backward being -1
     * @return The Y axis value of the left drive Joystick
     */
    public abstract double getLeftDriveY();

    /**
     * Returns the Y axis value of the right drive Joystick with forward being 1 and backward being -1
     * @return The Y axis value of the right drive Joystick
     */
    public abstract double getRightDriveY();

    /**
     * Returns the X axis value of the left driveJoystick with to the right being 1 and to the left being -1
     * @return The X axis value of the left drive Joystick
     */
    public abstract double getLeftDriveX();

    /**
     * Returns the X axis value of the left drive Joystick with to the right being 1 and to the left being -1
     * @return The X axis value of the left drive Joystick
     */
    public abstract double getRightDriveX();

    /**
     * Returns the 1st axis value of the controller Joystick from 1 to -1
     *
     * @return The 1st axis value of the controller Joystick
     */
    public abstract double getControllerAxis1();

    /**
     * Returns the 2nd axis value of the controller Joystick from 1 to -1
     *
     * @return The 2nd axis value of the controller Joystick
     */
    public abstract double getControllerAxis2();

    /**
     * Maps the specified command to the specified button
     * @param btn The location of the button
     * @param cmd The command to map
     * @param type The type of mapping
     */
    public abstract void mapButton(ButtonType btn, Command cmd, MappingType type);
}
