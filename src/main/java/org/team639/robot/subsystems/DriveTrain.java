package org.team639.robot.subsystems;

import com.ctre.MotorControl.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;
import org.team639.robot.commands.JoystickDrive;

/**
 * Contains all methods relating to the drivetrain
 */
public class DriveTrain extends Subsystem {
    private CANTalon leftDrive;
    private CANTalon rightDrive;

    public DriveTrain() {
        leftDrive = RobotMap.getLeftDrive();
        rightDrive = RobotMap.getRightDrive();
    }

    /**
     * Defines drive modes
     */
    public enum DriveMode {
        TANK,
        ARCADE_1_JOYSTICK,
        ARCADE_2_JOYSTICK
    }

    /**
     * Sets the default command to run while no others are running
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    /**
     * Sets the speed of the right side motors
     * @param speed The speed of the right side motors
     */
    public void setRightSide(double speed) {
        rightDrive.set(-1 * speed);
    }

    /**
     * Sets the speed of the left side motors
     * @param speed The speed of the left side motors
     */
    public void setLeftSide(double speed) {
        leftDrive.set(speed);
    }

    /**
     * Takes to speed values from -1 to 1 and uses them for tank driving
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void tankDrive(double lSpeed, double rSpeed) {
        setLeftSide(lSpeed);
        setRightSide(rSpeed);
    }

    /**
     * Performs arcade driving
     * @param speed The magnitude of speed from -1 to 1
     * @param turning The turning magnitude from -1 to 1
     */
    public void arcadeDrive(double speed, double turning) {
        setLeftSide(speed / 2 + turning / 3);
        setRightSide(speed / 2 - turning / 3);
    }
}
