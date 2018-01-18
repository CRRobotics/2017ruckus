package org.team639.lib.sensor;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Represents a distance sensor
 */
public abstract class DistanceSensor {
    private double offset = 0;
    private AnalogInput input;

    public DistanceSensor(int port) {
        input = new AnalogInput(port);
    }

    public DistanceSensor(int port, double offset) {
        this(port);
        this.offset = offset;
    }

    /**
     * Returns the distance in inches.
     * @return The distance in inches.
     */
    public double getDistance() {
        double d = calculateDistance();
        return d < 0 ? -1 : d - offset;
    }

    protected abstract double calculateDistance();
}
