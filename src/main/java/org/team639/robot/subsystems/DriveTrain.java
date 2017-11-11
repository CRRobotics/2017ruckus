package org.team639.robot.subsystems;

import com.ctre.MotorControl.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.Constants;
import org.team639.robot.RobotMap;
import org.team639.robot.commands.Drive.JoystickDrive;

/**
 * Contains all methods relating to the drivetrain
 */
public class DriveTrain extends Subsystem {
    private CANTalon leftDrive;
    private CANTalon rightDrive;

    private CANTalon.TalonControlMode currentControlMode;

    /**
     * Defines drive modes
     */
    public enum DriveMode {
        TANK,
        ARCADE_1_JOYSTICK,
        ARCADE_2_JOYSTICK
    }

    public DriveTrain() {
        leftDrive = RobotMap.getLeftDrive();
        rightDrive = RobotMap.getRightDrive();

        leftDrive.setAllowableClosedLoopErr(0);
        rightDrive.setAllowableClosedLoopErr(0);

        setCurrentControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    /**
     * Returns the current Talon control mode
     * @return The current Talon control mode
     */
    public CANTalon.TalonControlMode getCurrentControlMode() {
        return currentControlMode;
    }

    /**
     * Sets the Talon control mode
     * @param mode The control mode to set the talons to
     */
    public void setCurrentControlMode(CANTalon.TalonControlMode mode) {
        currentControlMode = mode;
        leftDrive.changeControlMode(currentControlMode);
        rightDrive.changeControlMode(currentControlMode);
    }

    /**
     * Sets the PID constants
     * @param p
     * @param i
     * @param d
     */
    public void setPID(double p, double i, double d) {
        rightDrive.setPID(p, i, d);
        leftDrive.setPID(p, i, d);
    }

    /**
     * Sets the default command to run while no others are running
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    /**
     * Takes to speed values from -1 to 1 and uses them to set the motors
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void setSpeeds(double lSpeed, double rSpeed) {
        switch (currentControlMode) {
            case PercentVbus:
                rightDrive.set(-1 * rSpeed);
                leftDrive.set(lSpeed);
                break;
            case Speed:
                rightDrive.set( -1 * rSpeed * Constants.DriveTrain.SPEED_RANGE);
                leftDrive.set(lSpeed * Constants.DriveTrain.SPEED_RANGE);
                break;
        }
    }

    /**
     * Takes to speed values from -1 to 1 and uses them for tank driving
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void tankDrive(double lSpeed, double rSpeed) {
        setSpeeds(lSpeed, rSpeed);
    }

    /**
     * Performs arcade driving
     * @param speed The magnitude of speed from -1 to 1
     * @param turning The turning magnitude from -1 to 1
     */
    public void arcadeDrive(double speed, double turning) {
        setSpeeds(speed / 2 + turning / 3, speed / 2 - turning / 3);
    }

    /**
     * Returns the position of the left encoder
     * @return The position of the left encoder
     */
    public int getLeftEncPos() {
        return leftDrive.getEncPosition();
    }

    /**
     * Reutrns the position of the right encoder
     * @return The position of the right encoder
     */
    public int getRightEncPos() {
        return rightDrive.getEncPosition();
    }

    /**
     * Returns the velocity of the left encoder
     * @return The velocity of the left encoder
     */
    public int getLeftEncVelocity() {
        return leftDrive.getEncVelocity();
    }

    /**
     * Returns the velocity of the right encoder
     * @return The velocity of the right encoder
     */
    public int getRightEncVelocity() {
        return -1 * rightDrive.getEncVelocity();
    }
}
