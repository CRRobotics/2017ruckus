package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.Constants;
import org.team639.robot.OI;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

public class JoystickDrive extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick stick;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(driveTrain);

//        leftStick = OI.getLeftDriveStick();
//        rightStick = OI.getRightDriveStick();
        stick = OI.getStick();
    }

    protected void initialize() {
        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.D);
        driveTrain.setPID(p,i,d);

        if (Robot.getTalonMode() != driveTrain.getCurrentControlMode()) {
            driveTrain.setCurrentControlMode(Robot.getTalonMode());
        }
    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
        DriveTrain.DriveMode mode = Robot.getDriveMode(); //Get drive mode from SmartDashboard
        switch (mode) {
            case TANK:
                driveTrain.tankDrive(stick.getRawAxis(1), stick.getRawAxis(5));
//                System.out.println(stick.getRawAxis(1) + " " + stick.getRawAxis(5));
                break;
            case ARCADE_1_JOYSTICK:
                driveTrain.arcadeDrive(stick.getRawAxis(5), stick.getRawAxis(4));
                break;
            case ARCADE_2_JOYSTICK:
                driveTrain.arcadeDrive(leftStick.getRawAxis(0), leftStick.getRawAxis(5));
                break;
        }
    }

    /**
     * Returns whether or not the command is finished
     * @return whether or not the command is finished
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}
