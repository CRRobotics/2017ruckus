package org.team639.robot;

public class PID {

    double kP;
    double kI;
    double kD;
    double lastInput;
    double outputSum;
    double outMax;
    double outMin;
    double lastOutput;
    double rate;
    double deadBand;
    double minDrive;

    public PID(double P, double I, double D, double max, double min, double UserRate, double DeadBand, double MinDrive){
        kP = P;
        kI = I;
        kD = D;
        outMax = max;
        outMin = min;
        lastInput = 0;
        lastOutput = 0;
        rate = UserRate;
        deadBand = DeadBand;
        minDrive = MinDrive;
    }

    public void SetPID(double P, double I, double D){
        kP = P;
        kI = I;
        kD = D;

    }

    public double Compute(double error)
    {
        if (Math.abs(error) < deadBand) return 0;

        //Compute all the working error variables*/
        double dInput = (error - lastInput);
        outputSum += (kI * error);
        // Cap how big the I value can grow
        if (outputSum > .2) outputSum = .2;
        if (outputSum < -.2) outputSum = -.2;

        double output = kP * error;

        if (Math.abs(output) < minDrive){
            if (output > 0){
                output = minDrive;
            }
            else{
                output = minDrive*(-1);
            }
        }

 /*       if (output < 0) {
            output = Math.sqrt(output * -1) * -1;
        }
        else{
            output = Math.sqrt(output);
        }
*/
        //Compute Rest of PID Output
        output += outputSum + kD * dInput;


        // ramp the output acceleration.  Only add rate to each increment
        if (output < 0){
          if ( output < lastOutput - rate) {
              output = lastOutput - rate;
          }
        }
        else {
          if (output > lastOutput + rate){
              output = lastOutput + rate;
          }
        }
/*
        if (Math.abs(output) < minDrive){
            if (output > 0){
                output = minDrive;
            }
            else{
                output = minDrive*(-1);
            }
        }
*/
        lastOutput = output;

        if(output > outMax) output = outMax;
        else if(output < outMin) output = outMin;

        //Remember some variables for next time
        lastInput = error;
        return output;

    }

}
