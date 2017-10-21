package org.team639.robot.subsystems;

import com.ctre.MotorControl.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;
import org.team639.robot.commands.JoystickDrive;

public class DriveTrain extends Subsystem {
    private CANTalon leftDrive;
    private CANTalon rightDrive;

    public DriveTrain() {
        leftDrive = RobotMap.getLeftDrive();
        rightDrive = RobotMap.getRightDrive();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    public void setRightSide(double speed) {
        rightDrive.set(speed);
    }

    public void setLeftSide(double speed) {
        leftDrive.set(speed);
    }

    public void tankDrive(double lspeed, double rspeed) {
        setLeftSide(lspeed);
        setRightSide(rspeed);
    }
}
