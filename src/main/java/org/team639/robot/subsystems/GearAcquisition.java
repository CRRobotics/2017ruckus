package org.team639.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;

/**
 * Contains all methods relating to the Gear Acquisition
 */
public class GearAcquisition extends Subsystem { //Cherry236

    private Solenoid gearClawClose;
    private Solenoid gearClawRaise;

    public GearAcquisition() {
        gearClawClose = RobotMap.getGearClawClose();
        gearClawRaise = RobotMap.getGearClawRaise();
    }

    /**
     * Sets the default command to run while no others are running
     */
    @Override
    protected void initDefaultCommand() {

    }

    /**
     * Opens the claw
     */
    public void open() {
        gearClawClose.set(true);
    }

    /**
     * Closes the claw
     */
    public void close() {
        gearClawClose.set(false);
    }

    /**
     * Checks if the claw is open
     * @return a boolean representing whether or not the claw is open
     */
    public boolean isOpen() {
        return gearClawClose.get();
    }

    /**
     * Detects whether a gear is in position in the claw
     * TODO: Just a placeholder for the moment. Needs to be replaced with real detection
     * @return Whether or not a gear is detected
     */
    public boolean gearDetected() {
        return false;
    }

    /**
     * Raises the claw
     */
    public void raise() {
        gearClawRaise.set(true);
    }

    /**
     * Lowers the claw
     */
    public void lower() {
        gearClawRaise.set(false);
    }

    /**
     * Checks if the claw is currently lowered
     * @return Whether or not the claw is lowered
     */
    public boolean isLowered() {
        return !gearClawRaise.get();
    }
}
