package org.team639.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    private static SendableChooser<ControlMode> talonMode;

    public static GearAcquisition getGearAcquisition() {
        return gearAcquisition;
    }

    public static DriveTrain getDriveTrain() {
        return driveTrain;
    }

    public static DriveTrain.DriveMode getDriveMode() {
        return driveMode.getSelected();
    }

    public static ControlMode getTalonMode() {
        return talonMode.getSelected();
    }

    @Override
    public void robotInit() {
        RobotMap.init();

        //Allows drivers to select drive modes
        driveMode = new SendableChooser<>();
        driveMode.addObject("Tank", DriveTrain.DriveMode.TANK);
        driveMode.addObject("Field Oriented 1 joystick", DriveTrain.DriveMode.FIELD_1_JOYSTICK);
        driveMode.addObject("Field Oriented 2 joysticks", DriveTrain.DriveMode.FIELD_2_JOYSTICK);
        driveMode.addObject("2 Joystick Arcade", DriveTrain.DriveMode.ARCADE_2_JOYSTICK);
        driveMode.addDefault("1 Joystick Arcade", DriveTrain.DriveMode.ARCADE_1_JOYSTICK);
        SmartDashboard.putData("Drive Mode", driveMode);

        //Activate and deactivate closed loop drive
        talonMode = new SendableChooser<>();
        talonMode.addObject("Open Loop", ControlMode.PercentOutput);
        talonMode.addDefault("Closed loop", ControlMode.Velocity);
        SmartDashboard.putData("Talon Control", talonMode);

        //PID constants -- Caution! May be used for multiple different operations and should be adjusted correctly for each.
        SmartDashboard.putNumber("drive p", 0.03);
        SmartDashboard.putNumber("drive i", 0);
        SmartDashboard.putNumber("drive d", 0.15);
        SmartDashboard.putNumber("rate", 0.015);
        SmartDashboard.putNumber("tolerance", 2);
        SmartDashboard.putNumber("min", 0.11);
        SmartDashboard.putNumber("max", 0.25);
        SmartDashboard.putNumber("iCap", 0.2);


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
//        System.out.println(driveTrain.getRobotYaw());
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