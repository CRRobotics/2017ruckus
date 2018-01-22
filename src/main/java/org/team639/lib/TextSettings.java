package org.team639.lib;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class TextSettings {

    /**
     * this main is only for testing
     */
    public static void main(String[] args)
    {
        System.out.println(getDouble("MIN_DRIVE_PERCENT"));
        System.out.println(getDouble("MIN_DRIVE_PERCEN"));
    }

    /**
     * create file and write keys and vals to it
     */
    private static void writeProps() {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            //driving constants
            prop.setProperty("MIN_DRIVE_PERCENT", "" + 0.11);
            prop.setProperty("SPEED_RANGE", "" + 3400);
            prop.setProperty("DRIVE_P", "" + 1.6);
            prop.setProperty("DRIVE_I", "" + 0);
            prop.setProperty("DRIVE_D", "" + 0);
            prop.setProperty("DRIVE_F", "" + 0.25);
            prop.setProperty("ARCADE_RATE", "" + 0.03);

            //wheel constants
            prop.setProperty("WHEEL_DIAMETER_INCHES", "" + 6);
            prop.setProperty("ENC_TICKS_PER_ROTATION", "" + 4096);
            prop.setProperty("DRIVE_FORWARD_TOLERANCE", "" + 200);

            // field oriented drive turning constants
            prop.setProperty("FOT_P", "" + 0.03);
            prop.setProperty("FOT_I", "" + 0);
            prop.setProperty("FOT_D", "" + 0.15);
            prop.setProperty("FOT_MIN", "" + 0.11);
            prop.setProperty("FOT_MAX", "" + 0.25);
            prop.setProperty("FOT_RATE", "" + 0.015);
            prop.setProperty("FOT_I_CAP", "" + 0.2);
            prop.setProperty("FOT_TOLERANCE", "" + 2);

            //Turn To Angle constants
            prop.setProperty("TTA_P", "" + 0.007);
            prop.setProperty("TTA_I", "" + 0);
            prop.setProperty("TTA_D", "" + 0);
            prop.setProperty("TTA_MIN", "" + 0.09);
            prop.setProperty("TTA_MAX", "" + 1);
            prop.setProperty("TTA_RATE", "" + 0.01);
            prop.setProperty("TTA_I_CAP", "" + 0.2);
            prop.setProperty("TTA_TOLERANCE", "" + 2);

            //other
            prop.setProperty("JOYSTICK_DEADZONE", "" + 0.05);

            // saves properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * an number accessor for values in the config file
     * @param key
     * @return keys value
     */
    public static Double getDouble(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        Double num = null;

        if(key.equals("WHEEL_CIRCUMFERENCE_INCHES"))
            return Math.PI * getDouble("WHEEL_DIAMETER_INCHES");
        else if(key.equals("TICKS_PER_INCH"))
            return getDouble("ENC_TICKS_PER_ROTATION")/getDouble("WHEEL_CIRCUMFERENCE_INCHES");
        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);
            if(prop.getProperty(key)!= null)
                num = Double.parseDouble(prop.getProperty(key));

        } catch (IOException ex) { ex.printStackTrace(); }
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return num;
        } else {
            writeProps();
            return getDouble(key);
        }
    }

    /**
     * an string accessor for values in the config file
     * @param key
     * @return keys value
     */
    public static String getString(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String word = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);
            word = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return word;
        } else {
            writeProps();
            return getString(key);
        }
    }
}
