package org.team639.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;

/**
 * Contains all methods relating to the Gear Acquisition
 */
public class GearAcquisition extends Subsystem { //Cherry236

    private Solenoid gearClawOpen;
    private Solenoid gearClawRaise;

    public GearAcquisition() {
        gearClawOpen = RobotMap.getGearClawOpen();
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
        gearClawOpen.set(true);
    }

    /**
     * Closes the claw
     */
    public void close() {
        gearClawOpen.set(false);
    }

    /**
     * Checks if the claw is open
     * @return a boolean representing whether or not the claw is open
     */
    public boolean isOpen() {
        return gearClawOpen.get();
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
        gearClawRaise.set(false);
    }

    /**
     * Lowers the claw
     */
    public void lower() {
        gearClawRaise.set(true);
    }

    /**
     * Checks if the claw is currently lowered
     * @return Whether or not the claw is lowered
     */
    public boolean isLowered() {
        return !gearClawRaise.get();
    }
}
