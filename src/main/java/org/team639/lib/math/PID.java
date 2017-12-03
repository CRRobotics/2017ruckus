package org.team639.lib.math;

public class PID {
    private double kP;
    private double kI;
    private double kD;
    private double min;
    private double max;
    private double rate;
    private double tolerance;
    private double iCap;

    private double totalError;
    private double lastError;
    private double lastOutput;

    public PID(double p, double i, double d, double min, double max, double rate, double tolerance, double iCap) {
        kP = p;
        kI = i;
        kD = d;
        this.min = min;
        this.max = max;
        this.rate = rate;
        this.tolerance = tolerance;
        this.iCap = iCap;

        totalError = 0;
        lastError = 0;
        lastOutput = 0;
    }

    public void setPID(double p, double i, double d) {
        kP = p;
        kI = i;
        kD = d;
    }

    public double compute(double error) {
        if (Math.abs(error) < tolerance) return 0;

        totalError += kI * error;

//        double iVal = kI * totalError;
        if (totalError > iCap) totalError = iCap;
        if (totalError < -1 * iCap) totalError = -1 * iCap;

        double output = kP * error;
        output += totalError;
        output += kD * (error - lastError);

        if (output < 0) {
            if (output < lastOutput - rate) output = lastOutput - rate;
            if (Math.abs(output) > max) output = -1 * max;
            if (Math.abs(output) < min) output = -1 * min;
        } else {
            if (output > lastOutput + rate) output = lastOutput + rate;
            if (output > max) output = max;
            if (output < min) output = min;
        }

        lastError = error;

        lastOutput = output;
        return output;
    }
}
