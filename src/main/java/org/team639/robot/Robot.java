package org.team639.robot;

import com.ctre.MotorControl.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.GearAcquisition;

public class Robot extends IterativeRobot {
    public static GearAcquisition gearAcquisition;
    public static DriveTrain drive;

    private Joystick leftStick;
    private Joystick rightStick;

    private CANTalon leftMotor;
    private CANTalon rightMotor;

    @Override
    public void robotInit() {
        RobotMap.init();
        OI.init();

//        leftStick = OI.getLeftDriveStick();
//        rightStick = OI.getRightDriveStick();
//
//        leftMotor = RobotMap.getLeftDrive();
//        rightMotor = RobotMap.getRightDrive();

        gearAcquisition = new GearAcquisition();
        drive = new DriveTrain();
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