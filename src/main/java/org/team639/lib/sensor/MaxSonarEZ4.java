package org.team639.lib.sensor;

import edu.wpi.first.wpilibj.AnalogInput;

public class MaxSonarEZ4 extends DistanceSensor{
    private AnalogInput input;

    public MaxSonarEZ4(int port) { super(port); }

    public double calculateDistance() { return input.getAverageVoltage() * 1000 / 9.8; }
}
