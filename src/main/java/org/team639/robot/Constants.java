package org.team639.robot;

public class Constants {
    public static class DriveTrain {
        public static final double SPEED_RANGE = 1500;
        public static final double P = 0.5;
        public static final double I = 0;
        public static final double D = 0;

        public static final double WHEEL_DIAMETER_INCHES = 6;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * Math.pow(WHEEL_DIAMETER_INCHES, 2);

        public static final int ENC_TICKS_PER_ROTATION = 1000;
    }
    public static final double OPEN_CLAW_TIME = 0.20;
    public static final double CLOSE_CLAW_TIME = 0.20;


}
