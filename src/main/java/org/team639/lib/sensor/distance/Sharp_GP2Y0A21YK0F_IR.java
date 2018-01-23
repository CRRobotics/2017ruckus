package org.team639.lib.sensor.distance;

import edu.wpi.first.wpilibj.AnalogInput;

public class Sharp_GP2Y0A21YK0F_IR implements DistanceSensor {
    AnalogInput input;

    /**
     * Constructs a new MaxSonarEZ4Analog
     * @param channel The analog channel to listen on.
     */
    public Sharp_GP2Y0A21YK0F_IR(int channel) {
        input = new AnalogInput(channel);
    }

    /**
     * Returns the distance registered in inches.
     *
     * @return the distance registered in inches.
     */
    @Override
//    public double getDistanceInches() {
//        double distance = (input.getVoltage() * ((80-10)/(0.55-0.25))-48.3)*0.3937;
//        return distance;
//    }

    public double getDistanceInches() {
        double distance = (Math.pow(input.getVoltage(), -1.243) * 27.07)*0.3937;
        return distance;
    }
}
