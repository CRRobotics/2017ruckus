package org.team639.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 */
public class OI {

    private static Joystick leftDriveStick;
    private static Joystick rightDriveStick;

    /**
     * Initializes all of the Joysticks and button mappings.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        leftDriveStick = new Joystick(0);
        rightDriveStick = new Joystick(1);
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
}
