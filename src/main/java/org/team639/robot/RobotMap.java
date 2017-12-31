package org.team639.robot;

import com.ctre.MotorControl.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Contains references to all of the motors, sensors, pneumatics, etc. Controls access by the rest of the code from a central location
 */
public class RobotMap {
    private static boolean initialized = false;

    private static CANTalon leftDrive;
    private static CANTalon rightDrive;

    private static Solenoid gearClawOpen;
    private static Solenoid gearClawRaise;

    private static DigitalInput gearSensor;

    private static AHRS ahrs;

    private RobotMap() {
    }

    /**
     * Initializes all of the motors, sensors, etc.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        if (!initialized) {
            leftDrive = new CANTalon(3);
            rightDrive = new CANTalon(1);

            gearClawOpen = new Solenoid(0);
            gearClawRaise = new Solenoid(1);

            gearSensor = new DigitalInput(0);

            initialized = true;

            ahrs = new AHRS(SPI.Port.kMXP);
        }
    }

    public static CANTalon getLeftDrive() {
        return leftDrive;
    }

    public static CANTalon getRightDrive() {
        return rightDrive;
    }

    public static Solenoid getGearClawOpen() {
        return gearClawOpen;
    }

    public static Solenoid getGearClawRaise() {
        return gearClawRaise;
    }

    public static DigitalInput getGearSensor() {
        return gearSensor;
    }

    public static AHRS getAhrs() {
        return ahrs;
    }
}
