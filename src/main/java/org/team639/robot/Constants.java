package org.team639.robot;

public class Constants {
    public static class DriveTrain {
        public static final double SPEED_RANGE = 15000;
        public static final double P = 0.1;
        public static final double I = 0;
        public static final double D = 0;

        // PID values for MotionMagic
        public static final double Pmm = 0.2;
        public static final double Imm = 0.001;
        public static final double Dmm = 20;

        // PID values for TurnToAngle
        public static final double Ptta = 0.03;
        public static final double Itta = 0.0;
        public static final double Dtta = .15;

        public static final double WHEEL_DIAMETER_INCHES = 6;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;

        public static final int ENC_TICKS_PER_ROTATION = 4096;
        public static final double TICKS_PER_INCH = ENC_TICKS_PER_ROTATION / WHEEL_CIRCUMFERENCE_INCHES;

        public static final double ANGLE_TO_INCH = .244;

        public static final double DRIVE_FORWARD_TOLERANCE = 500;
        public static final double TURN_TOLERANCE = 300;
        public static final double DRIVE_POSITION_VELOCITY_TOLERANCE = 10;
    }
    public static final double OPEN_CLAW_TIME = 0.20;
    public static final double CLOSE_CLAW_TIME = 0.20;


}
