package org.team639.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 */
public class OI {
    private static OI instance;

    private Joystick leftDriveStick;
    private Joystick rightDriveStick;

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }

    private OI() {
        leftDriveStick = new Joystick(0);
        rightDriveStick = new Joystick(1);
    }

    public Joystick getLeftDriveStick() {
        return leftDriveStick;
    }

    public Joystick getRightDriveStick() {
        return rightDriveStick;
    }
}
