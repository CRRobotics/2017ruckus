package org.team639.robot;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {
    public static class DriveTrain {
        public static final double MIN_DRIVE_PERCENT = 0.11; // TODO: Verify that this is correct.

        public static final double SPEED_RANGE = 3400;
        public static final double DRIVE_P = 1.6;
        public static final double DRIVE_I = 0;
        public static final double DRIVE_D = 0;
        public static final double DRIVE_F = 0.25;
        public static final double ARCADE_RATE = 0.03;

        public static final double WHEEL_DIAMETER_INCHES = 6;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;

        public static final int ENC_TICKS_PER_ROTATION = 4096;
        public static final double TICKS_PER_INCH = ENC_TICKS_PER_ROTATION / WHEEL_CIRCUMFERENCE_INCHES;

        public static final double DRIVE_FORWARD_TOLERANCE = 200;


        // field oriented drive turning constants
        public static final double FOT_P = 0.03;
        public static final double FOT_I = 0;
        public static final double FOT_D = 0.15;
        public static final double FOT_MIN = 0.11;
        public static final double FOT_MAX = 0.25;
        public static final double FOT_RATE = 0.015;
        public static final double FOT_I_CAP = 0.2;
        public static final double FOT_TOLERANCE = 2;
    }

    public static class Auto {
        //Turn To Angle constants
        public static final double TTA_P = 0.007;
        public static final double TTA_I = 0;
        public static final double TTA_D = 0;
        public static final double TTA_MIN = 0.09;
        public static final double TTA_MAX = 1;
        public static final double TTA_RATE = 0.01;
        public static final double TTA_I_CAP = 0.2;
        public static final double TTA_TOLERANCE = 2;
    }

    public static final double JOYSTICK_DEADZONE = 0.05;

    public static final double OPEN_CLAW_TIME = 0.20;
    public static final double CLOSE_CLAW_TIME = 0.20;

    private static HashMap<String, String> hm;
    static {

        hm = new HashMap<>();
        //driving constants
        hm.put("MIN_DRIVE_PERCENT", "" + 0.11);
        hm.put("SPEED_RANGE", "" + 3400);
        hm.put("DRIVE_P", "" + 1.6);
        hm.put("DRIVE_I", "" + 0);
        hm.put("DRIVE_D", "" + 0);
        hm.put("DRIVE_F", "" + 0.25);
        hm.put("ARCADE_RATE", "" + 0.03);

        //wheel constants
        hm.put("WHEEL_DIAMETER_INCHES", "" + 6);
        hm.put("ENC_TICKS_PER_ROTATION", "" + 4096);
        hm.put("DRIVE_FORWARD_TOLERANCE", "" + 200);

        // field oriented drive turning constants
        hm.put("FOT_P", "" + 0.03);
        hm.put("FOT_I", "" + 0);
        hm.put("FOT_D", "" + 0.15);
        hm.put("FOT_MIN", "" + 0.11);
        hm.put("FOT_MAX", "" + 0.25);
        hm.put("FOT_RATE", "" + 0.015);
        hm.put("FOT_I_CAP", "" + 0.2);
        hm.put("FOT_TOLERANCE", "" + 2);

        //Turn To Angle constants
        hm.put("TTA_P", "" + 0.007);
        hm.put("TTA_I", "" + 0);
        hm.put("TTA_D", "" + 0);
        hm.put("TTA_MIN", "" + 0.09);
        hm.put("TTA_MAX", "" + 1);
        hm.put("TTA_RATE", "" + 0.01);
        hm.put("TTA_I_CAP", "" + 0.2);
        hm.put("TTA_TOLERANCE", "" + 2);

        //other
        hm.put("JOYSTICK_DEADZONE", "" + 0.05);
    }

    public static String getConst(String key){ return hm.get(key); }

    public static Boolean hasConst(String key){ return hm.containsKey(key); }

    public static ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<>();
        for(String s : keys)
            keys.add(s);
        return keys;
    }


}
