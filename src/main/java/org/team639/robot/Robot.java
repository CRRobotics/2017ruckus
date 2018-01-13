package org.team639.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.GearAcquisition;

public class Robot extends TimedRobot {
    private static GearAcquisition gearAcquisition;
    private static DriveTrain driveTrain;

//    private double lMax = 0;
//    private double rMax = 0;

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
        driveMode.addDefault("1 Joystick Arcade", DriveTrain.DriveMode.ARCADE_1_JOYSTICK);
        driveMode.addObject("Tank", DriveTrain.DriveMode.TANK);
        driveMode.addObject("Field Oriented 1 joystick", DriveTrain.DriveMode.FIELD_1_JOYSTICK);
        driveMode.addObject("Field Oriented 2 joysticks", DriveTrain.DriveMode.FIELD_2_JOYSTICK);
        driveMode.addObject("2 Joystick Arcade", DriveTrain.DriveMode.ARCADE_2_JOYSTICK);
        driveMode.addObject("navx arcade", DriveTrain.DriveMode.NAVX_ARCADE);
        SmartDashboard.putData("Drive Mode", driveMode);

        //Activate and deactivate closed loop drive
        talonMode = new SendableChooser<>();
        talonMode.addDefault("Closed loop", ControlMode.Velocity);
        talonMode.addObject("Open Loop", ControlMode.PercentOutput);
        SmartDashboard.putData("Talon Control", talonMode);

        //PID constants -- Caution! May be used for multiple different operations and should be adjusted correctly for each.
        SmartDashboard.putNumber("drive p", 0.1);
        SmartDashboard.putNumber("drive i", 0);
        SmartDashboard.putNumber("drive d", 0);
        SmartDashboard.putNumber("drive f", 0);
        SmartDashboard.putNumber("rate", 0.1);
        SmartDashboard.putNumber("tolerance", 2);
        SmartDashboard.putNumber("min", 0.11);
        SmartDashboard.putNumber("max", 0.5);
        SmartDashboard.putNumber("iCap", 0.2);

        SmartDashboard.putNumber("turn speed", 1);
        SmartDashboard.putNumber("multiply by", 1);
//        SmartDashboard.putNumber("r max", rMax);
//        SmartDashboard.putNumber("l max", lMax);


//        SmartDashboard.putNumber("distance", 64);


        //Initialize subsystems
        gearAcquisition = new GearAcquisition();
        driveTrain = new DriveTrain();

        SmartDashboard.putNumber("rampRate", driveTrain.getRampRate());

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
//        double left = driveTrain.getLeftEncVelocity();
//        double right = driveTrain.getRightEncVelocity();
//        if (lMax < left) {
//            lMax = left;
//            SmartDashboard.putNumber("l max", lMax);
//        }
//
//        if (rMax < right) {
//            rMax = right;
//            SmartDashboard.putNumber("r max", rMax);
//        }
//        System.out.println("Left: " + driveTrain.getLeftEncPos() + ", Right: "  + driveTrain.getRightEncPos());
//        System.out.println("Robot yaw: " + driveTrain.getRobotYaw());
        SmartDashboard.putNumber("left velocity", driveTrain.getLeftEncVelocity());
        SmartDashboard.putNumber("right velocity", driveTrain.getRightEncVelocity());
//        SmartDashboard.putNumber("closed loop error", RobotMap.getRightDrive().getClosedLoopError(0));
//        SmartDashboard.putNumber("drive p", 0);
//        SmartDashboard.putNumber("drive i", 0);
//        SmartDashboard.putNumber("drive d", 0.);
//        SmartDashboard.putNumber("rate", 0.1);
//        SmartDashboard.putNumber("tolerance", 2);
//        SmartDashboard.putNumber("min", 0.11);
//        SmartDashboard.putNumber("max", 0.5);
//        SmartDashboard.putNumber("iCap", 0.2);
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