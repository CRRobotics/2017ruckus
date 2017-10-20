package org.team639.robot;

import com.ctre.MotorControl.CANTalon;

/**
 * Contains references to all of the motors, sensors, pneumatics, etc. Controls access by the rest of the code from a central location
 */
public class RobotMap {
    private static boolean initialized = false;

    private static CANTalon leftDrive;
    private static CANTalon rightDrive;

    private RobotMap() {
    }

    /**
     * Initializes all of the motors, sensors, etc.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        if (!initialized) {
            leftDrive = new CANTalon(1);
            rightDrive = new CANTalon(3);

            initialized = true;
        }
    }

    public static CANTalon getLeftDrive() {
        return leftDrive;
    }

    public static CANTalon getRightDrive() {
        return rightDrive;
    }
}
