package org.team639.lib.sensor;

/**
 * Represents a distance sensor
 */
public interface DistanceSensor {

    /**
     * Returns the distance registered in inches. Returns -1 if out of range.
     * @return the distance sensor in inches or -1 if out of range.
     */
    double getDistanceInches();
}
