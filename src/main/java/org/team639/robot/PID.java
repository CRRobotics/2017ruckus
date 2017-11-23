package org.team639.robot;

public class PID {

    double kP;
    double kI;
    double kD;
    double lastInput;
    double outputSum;
    double outMax;
    double outMin;
    public PID(double P, double I, double D, double max, double min){
        kP = P;
        kI = I;
        kD = D;
        outMax = max;
        outMin = min;
        lastInput = 0;
    }

    public void SetPID(double P, double I, double D){
        kP = P;
        kI = I;
        kD = D;

    }

    public double Compute(double error)
    {
      //Compute all the working error variables*/
 //     double error = setpoint - input;
      double dInput = (error - lastInput);
      outputSum += (kI * error);
      // Cap how big the I value can grow
        if (outputSum > .05) outputSum = .05;
        if (outputSum < -.05) outputSum = -.05;

      double output = kP * error;
/*        if (output < 0) {
            output = Math.sqrt(output * -1) * -1;
        }
        else{
            output = Math.sqrt(output);
        }
*/
      //Compute Rest of PID Output
      output += outputSum + kD * dInput;

      if(output > outMax) output = outMax;
      else if(output < outMin) output = outMin;

      //Remember some variables for next time
      lastInput = error;
      return output;

    }

}
