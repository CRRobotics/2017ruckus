package org.team639.robot;

public class AngleUtilities {

    static double mod(double a, int n)
    {
        return a - Math.floor(a/n) * n;
//	return a % n;
    }

    public static double angle_diff(double a, double b)
    {
        return mod((a-b) + 180, 360) - 180;
    }

    public static double formatAngle(double a)
    {
        return mod(a + 180, 360) - 180;
    }


}
