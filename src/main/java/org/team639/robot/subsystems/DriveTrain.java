package org.team639.robot.subsystems;

import com.ctre.MotorControl.CANTalon;
import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.PIDSourceType;
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

        leftDrive.setAllowableClosedLoopErr(10);
        rightDrive.setAllowableClosedLoopErr(10);

        leftDrive.setPIDSourceType(PIDSourceType.kRate);
        rightDrive.setPIDSourceType(PIDSourceType.kRate);

        leftDrive.setFeedbackDevice(SmartMotorController.FeedbackDevice.QuadEncoder);
        rightDrive.setFeedbackDevice(SmartMotorController.FeedbackDevice.QuadEncoder);

        leftDrive.reverseSensor(true);
        rightDrive.reverseSensor(true);

        setCurrentControlMode(CANTalon.TalonControlMode.PercentVbus);

        setPID(Constants.DriveTrain.P, Constants.DriveTrain.I, Constants.DriveTrain.D);
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
     * Takes two speed values from -1 to 1 and uses them to set the motors
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void setSpeedsPercent(double lSpeed, double rSpeed) {
        if (Math.abs(lSpeed) < Constants.JOYSTICK_DEADZONE) lSpeed = 0;
        if (Math.abs(rSpeed) < Constants.JOYSTICK_DEADZONE) rSpeed = 0;
        switch (currentControlMode) {
            case PercentVbus:
                rightDrive.set(-1 * rSpeed);
                leftDrive.set(lSpeed);
                break;
            case Speed:
                System.out.println("Right: " + getRightEncVelocity() + " Left: " + getLeftEncVelocity());
                rightDrive.set(-1 * rSpeed * Constants.DriveTrain.SPEED_RANGE);
                leftDrive.set(lSpeed * Constants.DriveTrain.SPEED_RANGE);
                break;
        }
    }

    /**
     * Takes two raw speed values and uses them to set the motors.
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void setSpeedsRaw(double lSpeed, double rSpeed) {
        rightDrive.set(-1 * rSpeed);
        leftDrive.set(lSpeed);
    }

    /**
     * Takes to speed values from -1 to 1 and uses them for tank driving
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void tankDrive(double lSpeed, double rSpeed) {
        setSpeedsPercent(lSpeed, rSpeed);
    }

    /**
     * Performs arcade driving
     * @param speed The magnitude of speed from -1 to 1
     * @param turning The turning magnitude from -1 to 1
     */
    public void arcadeDrive(double speed, double turning) {
        setSpeedsPercent(speed / 2 + turning / 3, speed / 2 - turning / 3);
    }

    /**
     * Returns the position of the left encoder
     * @return The position of the left encoder
     */
    public int getLeftEncPos() {
        return -1 * leftDrive.getEncPosition();
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
        return -1 * leftDrive.getEncVelocity();
    }

    /**
     * Returns the velocity of the right encoder
     * @return The velocity of the right encoder
     */
    public int getRightEncVelocity() {
        return rightDrive.getEncVelocity();
    }
}
