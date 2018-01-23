package org.team639.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.sensor.distance.DistanceSensor;
import org.team639.robot.Constants;
import org.team639.robot.RobotMap;
import org.team639.robot.commands.Drive.JoystickDrive;

/**
 * Contains all methods relating to the drivetrain
 */
public class DriveTrain extends Subsystem {
    private TalonSRX leftDrive;
    private TalonSRX rightDrive;

    private DistanceSensor frontSonar;

    private double kP;
    private double kI;
    private double kD;
    private double kF;

    private double rampRate = 0;

    private AHRS ahrs;

    private ControlMode currentControlMode;

    /**
     * Defines drive modes
     */
    public enum DriveMode {
        TANK,
        ARCADE_1_JOYSTICK,
        ARCADE_2_JOYSTICK,
        FIELD_1_JOYSTICK,
        FIELD_2_JOYSTICK
    }

    public DriveTrain() {
        leftDrive = RobotMap.getLeftDrive();
        rightDrive = RobotMap.getRightDrive();

        frontSonar = RobotMap.getFrontSonar();

        leftDrive.configAllowableClosedloopError(0,50,10);
        rightDrive.configAllowableClosedloopError(0,50,10);

        leftDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        rightDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

        leftDrive.setSensorPhase(true);
        rightDrive.setSensorPhase(true);

        leftDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
        rightDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);

        leftDrive.setNeutralMode(NeutralMode.Brake);
        rightDrive.setNeutralMode(NeutralMode.Brake);

        setCurrentControlMode(ControlMode.Velocity);

        setPID(Constants.DriveTrain.DRIVE_P, Constants.DriveTrain.DRIVE_I, Constants.DriveTrain.DRIVE_D, Constants.DriveTrain.DRIVE_F);
        setRampRate(rampRate);

        ahrs = RobotMap.getAhrs();
        ahrs.zeroYaw();
    }

    /**
     * Returns the current Talon control mode
     * @return The current Talon control mode
     */
    public ControlMode getCurrentControlMode() {
        return currentControlMode;
    }

    /**
     * Sets the Talon control mode
     * @param mode The control mode to set the talons to
     */
    public void setCurrentControlMode(ControlMode mode) {
        currentControlMode = mode;
//        leftDrive.changeControlMode(currentControlMode);
//        rightDrive.changeControlMode(currentControlMode);
    }

    /**
     * Sets the PID constants.
     * @param p The P constant.
     * @param i The I constant.
     * @param d The D constant.
     * @param f The F constant.
     */
    public void setPID(double p, double i, double d, double f) {
        kP = p;
        kI = i;
        kD = d;
        kF = f;
//        rightDrive.setPID(p, i, d);
        rightDrive.config_kP(0, kP, 10);
        rightDrive.config_kI(0, kI, 10);
        rightDrive.config_kD(0, kD, 10);
        rightDrive.config_kF(0, kF, 10);
//        leftDrive.setPID(p, i, d);
        leftDrive.config_kP(0, kP, 10);
        leftDrive.config_kI(0, kI, 10);
        leftDrive.config_kD(0, kD, 10);
        leftDrive.config_kF(0, kF, 10);

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
//        if (Math.abs(lSpeed) < Constants.JOYSTICK_DEADZONE) lSpeed = 0;
//        if (Math.abs(rSpeed) < Constants.JOYSTICK_DEADZONE) rSpeed = 0;

        // Limits speeds to the range [-1, 1]
        if (Math.abs(lSpeed) > 1) lSpeed = lSpeed < 0 ? -1 : 1;
        if (Math.abs(rSpeed) > 1) rSpeed = rSpeed < 0 ? -1 : 1;
        switch (currentControlMode) {
            case PercentOutput:
                setSpeedsRaw(lSpeed, rSpeed);
                break;
            case Velocity:
                SmartDashboard.putNumber("right setpoint", -1 * rSpeed * Constants.DriveTrain.SPEED_RANGE);
                SmartDashboard.putNumber("left setpoint", lSpeed * Constants.DriveTrain.SPEED_RANGE);
                double ls = lSpeed * Constants.DriveTrain.SPEED_RANGE;
                double rs = rSpeed * Constants.DriveTrain.SPEED_RANGE;
                setSpeedsRaw(ls, rs);
                break;
        }
    }

    /**
     * Takes two raw speed values and uses them to set the motors.
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void setSpeedsRaw(double lSpeed, double rSpeed) {


        rightDrive.set(currentControlMode, -1 * rSpeed);
        leftDrive.set(currentControlMode, lSpeed);
    }




    /**
     * Returns the position of the left encoder
     * @return The position of the left encoder
     */
    public int getLeftEncPos() {
        return leftDrive.getSelectedSensorPosition(0);
    }

    /**
     * Reutrns the position of the right encoder
     * @return The position of the right encoder
     */
    public int getRightEncPos() {
        return -1 * rightDrive.getSelectedSensorPosition(0);
    }

    /**
     * Returns the velocity of the left encoder
     * @return The velocity of the left encoder
     */
    public int getLeftEncVelocity() {
        return leftDrive.getSelectedSensorVelocity(0);
    }

    /**
     * Returns the velocity of the right encoder
     * @return The velocity of the right encoder
     */
    public int getRightEncVelocity() {
        return -1 * rightDrive.getSelectedSensorVelocity(0);
    }

    /**
     * Gets the current yaw of the robot from 0-180 degrees, with 90 being directly downfield. This assumes that the robot starts facing downfield.
     * @return The current yaw of the robot
     */
    public double getRobotYaw() {
        double angle = ahrs.getYaw();
        angle *= -1;
        angle += 90;
        if (angle < 0) angle = 360 + angle;
        return angle;
    }

    /**
     * Checks if the NavX is connected.
     * @return If the NavX is connected.
     */
    public boolean isNavXPresent() {
        return ahrs.isConnected();
    }

    /**
     * Zeroes the robot yaw.
     */
    public void zeroRobotYaw() {
        ahrs.zeroYaw();
    }

    /**
     * Returns the P constant of the drivetrain.
     * @return the P constant of the drivetrain.
     */
    public double getkP() {
        return kP;
    }

    /**
     * Returns the I constant of the drivetrain.
     * @return the I constant of the drivetrain.
     */
    public double getkI() {
        return kI;
    }

    /**
     * Returns the D constant of the drivetrain.
     * @return the D constant of the drivetrain.
     */
    public double getkD() {
        return kD;
    }

    /**
     * Returns the F constant of the drivetrain.
     * @return the F constant of the drivetrain.
     */
    public double getkF() {
        return kF;
    }

    /**
     * Returns the ramp rate of the drivetrain.
     * @return the ramp rate of the drivetrain.
     */
    public double getRampRate() {
        return rampRate;
    }

    /**
     * Sets the ramp rate of the drivetrain
     * @param rampRate The new ramp rate.
     */
    public void setRampRate(double rampRate) {
        this.rampRate = rampRate;
        leftDrive.configClosedloopRamp(rampRate, 10);
        rightDrive.configClosedloopRamp(rampRate, 10);
    }

    /**
     * Returns the distance detected by the distance sensor on the front of the robot.
     * @return the distance detected by the distance sensor on the front of the robot.
     */
    public double getFrontDistance() {
        return frontSonar.getDistanceInches();
    }
}
