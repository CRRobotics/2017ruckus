package org.team639.robot;

import com.ctre.MotorControl.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.GearAcquisition;

public class Robot extends IterativeRobot {
    public static GearAcquisition gearAcquisition;
    public static DriveTrain driveTrain;
    public static SendableChooser<Integer> driveMode;

    @Override
    public void robotInit() {
        RobotMap.init();

        driveMode = new SendableChooser<>();
        driveMode.addDefault("Tank", 0);
        driveMode.addObject("2 Joystick Arcade", 1);
        driveMode.addObject("1 Joystick Arcade", 2);
        SmartDashboard.putData("Drive Mode", driveMode);

        gearAcquisition = new GearAcquisition();

        OI.init();
        System.out.println(gearAcquisition);
        driveTrain = new DriveTrain();
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
//        leftMotor.set(leftStick.getY());
//        rightMotor.set((rightStick.getY()) * -1);
    }

    @Override
    public void testPeriodic() {
        super.testPeriodic();
    }
}