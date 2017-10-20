package org.team639.robot;

import com.ctre.MotorControl.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {
    private OI oi;

    private CANTalon leftDrive;
    private CANTalon rightDrive;

    private Joystick leftStick;
    private Joystick rightStick;

    @Override
    public void robotInit() {
        RobotMap.init();
        oi = OI.getInstance();

        leftDrive = RobotMap.getLeftDrive();
        rightDrive = RobotMap.getRightDrive();

        leftStick = oi.getLeftDriveStick();
        rightStick = oi.getRightDriveStick();
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void testInit() {

    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {
        double left = leftStick.getAxis(Joystick.AxisType.kY);
//        double right = rightStick.getAxis(Joystick.AxisType.kY);
        double off = leftStick.getAxis(Joystick.AxisType.kX);

//        double avg = (left + right) / 2;

//        System.out.println("" + left + " " + right);
//        leftDrive.set((left + avg) / 2);
//        rightDrive.set(-1 * ((right + avg) / 2));
        leftDrive.set((left + off));
        rightDrive.set(-1 * (left - off));
    }

    @Override
    public void testPeriodic() {

    }
}
