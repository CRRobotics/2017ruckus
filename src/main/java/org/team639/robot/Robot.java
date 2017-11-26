package org.team639.robot;

import com.ctre.MotorControl.CANTalon;
import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.GearAcquisition;

public class Robot extends IterativeRobot {
    private static GearAcquisition gearAcquisition;
    private static DriveTrain driveTrain;

    private static SendableChooser<DriveTrain.DriveMode> driveMode;
    private static SendableChooser<CANTalon.TalonControlMode> talonMode;

    public static GearAcquisition getGearAcquisition() {
        return gearAcquisition;
    }

    public static DriveTrain getDriveTrain() {
        return driveTrain;
    }

    public static DriveTrain.DriveMode getDriveMode() {
        return driveMode.getSelected();
    }

    public static CANTalon.TalonControlMode getTalonMode() {
        return talonMode.getSelected();
    }

    @Override
    public void robotInit() {
        RobotMap.init();

        //Allows drivers to select drive modes
        driveMode = new SendableChooser<>();
        driveMode.addDefault("Tank", DriveTrain.DriveMode.TANK);
        driveMode.addObject("2 Joystick Arcade", DriveTrain.DriveMode.ARCADE_2_JOYSTICK);
        driveMode.addObject("1 Joystick Arcade", DriveTrain.DriveMode.ARCADE_1_JOYSTICK);
        SmartDashboard.putData("Drive Mode", driveMode);

        //Activate and deactivate closed loop drive
        talonMode = new SendableChooser<>();
        talonMode.addObject("Open Loop", CANTalon.TalonControlMode.PercentVbus);
        talonMode.addDefault("Closed loop", CANTalon.TalonControlMode.Speed);
        SmartDashboard.putData("Talon Control", talonMode);

        //PID constants
        SmartDashboard.putNumber("drive p", Constants.DriveTrain.DRIVE_P);
        SmartDashboard.putNumber("drive i", Constants.DriveTrain.DRIVE_I);
        SmartDashboard.putNumber("drive d", Constants.DriveTrain.DRIVE_I);

        //Initialize subsystems
        gearAcquisition = new GearAcquisition();
        driveTrain = new DriveTrain();

        OI.mapButtons();
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
    }

    @Override
    public void testInit() {
        super.testInit();
    }

    @Override
    public void robotPeriodic() {
        System.out.println(driveTrain.getLeftEncPos() + " " + driveTrain.getRightEncPos());
        super.robotPeriodic();
    }

    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
        super.testPeriodic();
    }
}