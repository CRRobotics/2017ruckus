package org.team639.lib.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import java.util.HashMap;
import java.util.Map;

public class LogitechF310 extends JoystickManager {

    private Joystick stick;
    private Map<Buttons, Button> btns;

    public enum Buttons implements ButtonType {
        X,
        Y,
        A,
        B,
        LB,
        RB,
        LeftJoyPress,
        RightJoyPress
    }

    /**
     * Constructs a new Logitech F310 using port 0
     */
    public LogitechF310() {
        stick = new Joystick(0);
        initBtns();
    }

    /**
     * Constructs a new Logitech F310 using the specified port
     * @param port The USB port of the gamepad
     */
    public LogitechF310(int port) {
        stick = new Joystick(port);
        initBtns();
    }

    private void initBtns() {
        btns = new HashMap<>();
        btns.put(Buttons.A, new JoystickButton(stick, 1));
        btns.put(Buttons.B, new JoystickButton(stick, 2));
        btns.put(Buttons.X, new JoystickButton(stick, 3));
        btns.put(Buttons.Y, new JoystickButton(stick, 4));
        btns.put(Buttons.LB, new JoystickButton(stick, 5));
        btns.put(Buttons.RB, new JoystickButton(stick, 6));
        btns.put(Buttons.LeftJoyPress, new JoystickButton(stick, 9));
        btns.put(Buttons.RightJoyPress, new JoystickButton(stick, 10));
    }


    /**
     * Returns the Y axis value of the left drive Joystick with forward being 1 and backward being -1
     *
     * @return The Y axis value of the left drive Joystick
     */
    @Override
    public double getLeftDriveY() {
        return -1 * stick.getRawAxis(1);
    }

    /**
     * Returns the Y axis value of the right drive Joystick with forward being 1 and backward being -1
     *
     * @return The Y axis value of the right drive Joystick
     */
    @Override
    public double getRightDriveY() {
        return -1 * stick.getRawAxis(5);
    }

    /**
     * Returns the X axis value of the left driveJoystick with to the right being 1 and to the left being -1
     *
     * @return The X axis value of the left drive Joystick
     */
    @Override
    public double getLeftDriveX() {
        return stick.getRawAxis(0);
    }

    /**
     * Returns the X axis value of the left drive Joystick with to the right being 1 and to the left being -1
     *
     * @return The X axis value of the left drive Joystick
     */
    @Override
    public double getRightDriveX() {
        return stick.getRawAxis(4);
    }

    /**
     * Returns the 1st axis value of the controller Joystick from 1 to -1
     *
     * @return The 1st axis value of the controller Joystick
     */
    @Override
    public double getControllerAxis1() {
        return stick.getRawAxis(2) * 2 - 1;
    }

    /**
     * Returns the 2nd axis value of the controller Joystick from 1 to -1
     *
     * @return The 2nd axis value of the controller Joystick
     */
    @Override
    public double getControllerAxis2() {
        return stick.getRawAxis(3) * 2 - 1;
    }

    @Override
    public boolean getButtonRaw(ButtonType btn){
        return btns.get(btn).get();
    }
    /**
     * Maps the specified command to the specified button
     *
     * @param btn The location of the button
     * @param cmd The command to map
     * @param type The type of mapping
     */
    @Override
    public void mapButton(ButtonType btn, Command cmd, MappingType type) {
        if (!(btn instanceof Buttons)) {
            System.out.println("Missing button on LogitechF310. Are you sure you're using the correct enum?");
            return;
        }
        Button b = btns.get(btn);
        switch (type) {
            case WhenPressed:
                b.whenPressed(cmd);
                break;
            case WhenReleased:
                b.whenReleased(cmd);
                break;
            case WhileHeld:
                b.whileHeld(cmd);
                break;
            case CancelWhenPressed:
                b.cancelWhenPressed(cmd);
                break;
            case ToggleWhenPressed:
                b.toggleWhenPressed(cmd);
                break;
        }
    }
}
