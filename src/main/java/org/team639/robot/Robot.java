package org.team639.robot;

import com.ctre.MotorControl.SmartMotorController;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.GearAcquisition;

public class Robot extends IterativeRobot {
    public static GearAcquisition gearAcquisition;
    public static DriveTrain driveTrain;

    public static SendableChooser<DriveTrain.DriveMode> driveMode;
    public static SendableChooser<SmartMotorController.TalonControlMode> talonMode;

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
        talonMode.addDefault("Open Loop", SmartMotorController.TalonControlMode.PercentVbus);
        talonMode.addObject("Closed loop", SmartMotorController.TalonControlMode.Speed);
        SmartDashboard.putData("Talon Control", talonMode);

        //PID constants
        SmartDashboard.putNumber("drive p", Constants.DriveTrain.P);
        SmartDashboard.putNumber("drive i", Constants.DriveTrain.I);
        SmartDashboard.putNumber("drive d", Constants.DriveTrain.D);

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