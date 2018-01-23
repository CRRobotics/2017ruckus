package org.team639.robot;

import static org.team639.lib.TextSettings.*;

public class ConstAlt {
        public static final double MIN_DRIVE_PERCENT = getDouble("MIN_DRIVE_PERCENT");

        public static final double SPEED_RANGE = getDouble("SPEED_RANGE");
        public static final double DRIVE_P = getDouble("DRIVE_P");
        public static final double DRIVE_I = getDouble("DRIVE_I");
        public static final double DRIVE_D = getDouble("DRIVE_D");
        public static final double DRIVE_F = getDouble("DRIVE_F");
        public static final double ARCADE_RATE = getDouble("ARCADE_RATE");

        public static final double WHEEL_DIAMETER_INCHES = getDouble("WHEEL_DIAMETER_INCHES");
        public static final double WHEEL_CIRCUMFERENCE_INCHES = getDouble("WHEEL_CIRCUMFERENCE_INCHES");

        public static final int ENC_TICKS_PER_ROTATION = (int)(double)(getDouble("ENC_TICKS_PER_ROTATION"));
        public static final double TICKS_PER_INCH = getDouble("TICKS_PER_INCH");

        public static final double DRIVE_FORWARD_TOLERANCE = getDouble("DRIVE_FORWARD_TOLERANCE");


        // field oriented drive turning constants
        public static final double FOT_P = getDouble("FOT_P");
        public static final double FOT_I = getDouble("FOT_I");
        public static final double FOT_D = getDouble("FOT_D");
        public static final double FOT_MIN = getDouble("FOT_MIN");
        public static final double FOT_MAX = getDouble("FOT_MAX");
        public static final double FOT_RATE = getDouble("FOT_RATE");
        public static final double FOT_I_CAP = getDouble("FOT_I_CAP");
        public static final double FOT_TOLERANCE = getDouble("FOT_TOLERANCE");

        //Turn To Angle constants
        public static final double TTA_P = getDouble("TTA_P");
        public static final double TTA_I = getDouble("TTA_I");
        public static final double TTA_D = getDouble("TTA_D");
        public static final double TTA_MIN = getDouble("TTA_MIN");
        public static final double TTA_MAX = getDouble("TTA_MAX");
        public static final double TTA_RATE = getDouble("TTA_RATE");
        public static final double TTA_I_CAP = getDouble("TTA_I_CAP");
        public static final double TTA_TOLERANCE = getDouble("TTA_TOLERANCE");

    public static final double JOYSTICK_DEADZONE = getDouble("JOYSTICK_DEADZONE");
}
