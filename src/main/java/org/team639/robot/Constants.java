package org.team639.robot;

public class Constants {
    public static class DriveTrain {
        public static final double SPEED_RANGE = 15000;
        public static final double DRIVE_P = 0.1;
        public static final double DRIVE_I = 0;
        public static final double DRIVE_D = 0;

        public static final double WHEEL_DIAMETER_INCHES = 6;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;

        public static final int ENC_TICKS_PER_ROTATION = 4096;
        public static final double TICKS_PER_INCH = ENC_TICKS_PER_ROTATION / WHEEL_CIRCUMFERENCE_INCHES;

        public static final double DRIVE_FORWARD_TOLERANCE = 200;
    }

    public static class Auto {
        //Turn To Angle constants
        public static final double TTA_P = 0.0001;
        public static final double TTA_I = 0;
        public static final double TTA_D = 0;
        public static final double TTA_MIN = 0.2;
        public static final double TTA_MAX = 0.5;
        public static final double TTA_RATE = 0.1;
        public static final double TTA_I_CAP = 0.2;
        public static final double TTA_TOLERANCE = 2;
    }

    public static final double JOYSTICK_DEADZONE = 0.05;

    public static final double OPEN_CLAW_TIME = 0.20;
    public static final double CLOSE_CLAW_TIME = 0.20;


}
