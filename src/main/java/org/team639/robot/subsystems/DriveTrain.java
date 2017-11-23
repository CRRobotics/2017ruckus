package org.team639.robot.subsystems;

import com.ctre.MotorControl.CANTalon;
import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.Constants;
import org.team639.robot.PID;
import org.team639.robot.RobotMap;
import org.team639.robot.commands.Drive.JoystickDrive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import org.team639.robot.OI;

import static java.lang.Math.*;

/**
 * Contains all methods relating to the drivetrain
 */
public class DriveTrain extends Subsystem {
    private CANTalon leftDrive;
    private CANTalon rightDrive;
    AHRS ahrs;
    PID TurnPID;


    private CANTalon.TalonControlMode currentControlMode;
    private int lFinalPosition;  // Used to store the final position in ticks when moving to a position
    private int rFinalPosition;  // Used to store the final position in ticks when moving to a position
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

        try {
          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            ahrs = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException ex ) {
            System.out.println("Error instantiating navX-MXP:  " + ex.getMessage());
        }
      //  TurnPID = new PID(.008, 0, 0.1, .2, -.2);
        TurnPID = new PID(.04, 0, 0.15, .2, -.2);

        ahrs.reset();
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

        if( currentControlMode == CANTalon.TalonControlMode.MotionMagic) {
            leftDrive.setMotionMagicCruiseVelocity(500);
            leftDrive.setMotionMagicAcceleration(500);
            rightDrive.setMotionMagicCruiseVelocity(500);
            rightDrive.setMotionMagicAcceleration(500);
            setPID(Constants.DriveTrain.Pmm, Constants.DriveTrain.Imm, Constants.DriveTrain.Dmm);
            leftDrive.setF(0.29);
            rightDrive.setF(0.29);
        }
        else
        {
            setPID(Constants.DriveTrain.P, Constants.DriveTrain.I, Constants.DriveTrain.D);
        }
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
        if (lSpeed < 0.1 && lSpeed > -0.1) lSpeed = 0;
        if (rSpeed < 0.1 && rSpeed > -0.1) rSpeed = 0;
        switch (currentControlMode) {
            case PercentVbus:
                rightDrive.set(-1 * rSpeed);
                leftDrive.set(lSpeed);
                break;
            case Speed:
//                System.out.println("Right: " + getRightEncVelocity() + " Left: " + getLeftEncVelocity());
 //               System.out.println("Right Stick: " + rSpeed + " Left Stick: " + lSpeed);


                double LeftX = OI.manager.getLeftDriveX();
                double LeftY = OI.manager.getLeftDriveY();
                double DriveAngle = atan2(LeftX,LeftY)*57.296;
                double RobotAngle = formatAngle(ahrs.getYaw());
                double AngleError = angle_diff(DriveAngle, RobotAngle);
                double AngleSpeed = sqrt(LeftX*LeftX + LeftY*LeftY);

                AngleSpeed = TurnPID.Compute(AngleError);

/*                System.out.print(DriveAngle);
                System.out.print(", ");
                System.out.print(RobotAngle);
                System.out.print(", ");*/
                System.out.print(AngleError);
                System.out.print(", ");
                System.out.print(AngleSpeed);
                System.out.print(" ");
                System.out.println();

                if (AngleError > 5) AngleError = 5;
                if (AngleError < -5) AngleError = -5;
                if (abs(AngleError) < 3) AngleError = 0;


                rightDrive.set(-1 * (rSpeed - (AngleSpeed)) * Constants.DriveTrain.SPEED_RANGE);
                leftDrive.set(((lSpeed + AngleSpeed) ) * Constants.DriveTrain.SPEED_RANGE);
                break;
        }
    }

    /**
     * Takes two raw values and uses them to set the Talons.
     * Used for Position control
     * @param lSpeed The value for the left side
     * @param rSpeed The value for the right side
     */
    public void setRaw(double lVal, double rVal) {
        rightDrive.set(-1 * rVal);
        leftDrive.set(lVal);
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
     * @return The position of the left encoder. This value moves negative for forward.
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

    // Sets the Talons in MotionMagic mode to move a specified distance
    public void DriveToDistance(double distance)
    {
        setSpeedsPercent(0, 0);
        setCurrentControlMode(SmartMotorController.TalonControlMode.MotionMagic);

        lFinalPosition = getLeftEncPos() + (int)(distance * Constants.DriveTrain.TICKS_PER_INCH);
        rFinalPosition = getRightEncPos() + (int)(distance * Constants.DriveTrain.TICKS_PER_INCH);
        setRaw(lFinalPosition , rFinalPosition );
    }

    public boolean isDoneDriveToDistance()
    {
        boolean left = (Math.abs(getlFinalPosition() - getLeftEncPos()) < Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE);
        boolean right = (Math.abs(getrFinalPosition() - getRightEncPos()) < Constants.DriveTrain.DRIVE_FORWARD_TOLERANCE);
        return left && right;
    }

    public void DriveToAngle(double angle)
    {
        setSpeedsPercent(0, 0);
        setCurrentControlMode(SmartMotorController.TalonControlMode.MotionMagic);

        lFinalPosition = getLeftEncPos() + (int)(angle * Constants.DriveTrain.ANGLE_TO_INCH * Constants.DriveTrain.TICKS_PER_INCH);
        rFinalPosition = getRightEncPos() - (int)(angle * Constants.DriveTrain.ANGLE_TO_INCH * Constants.DriveTrain.TICKS_PER_INCH);
        setRaw(lFinalPosition , rFinalPosition );
    }

    public boolean isDoneDriveToAngle()
    {
        boolean left = (Math.abs(getlFinalPosition() - getLeftEncPos()) < Constants.DriveTrain.TURN_TOLERANCE);
        boolean right = (Math.abs(getrFinalPosition() - getRightEncPos()) < Constants.DriveTrain.TURN_TOLERANCE);
        return left && right;
    }


    public int getlFinalPosition() {return lFinalPosition;}
    public int getrFinalPosition() {return rFinalPosition;}


    double mod(double a, int n)
    {
        return a - floor(a/n) * n;
//	return a % n;
    }

    double angle_diff(double a, double b)
    {
        return mod((a-b) + 180, 360) - 180;
    }

    double formatAngle(double a)
    {
        return mod(a + 180, 360) - 180;
    }

    public void SetTurnPID(double p, double i, double d){
 //       TurnPID.SetPID(p, i, d);
    }
}

