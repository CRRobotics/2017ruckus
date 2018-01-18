package org.team639.lib.sensor;

import edu.wpi.first.wpilibj.AnalogInput;

public class MaxSonarEZ4 {
    private AnalogInput input;

    public MaxSonarEZ4(int port) {
        input = new AnalogInput(port);
    }

    public double getDistance() {
        return input.getAverageVoltage() * 1000 / 9.8;
    }
}
