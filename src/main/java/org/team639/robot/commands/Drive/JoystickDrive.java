package org.team639.robot.commands.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.*;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.lib.controls.LogitechF310.*;
import static java.lang.Math.sqrt;

public class JoystickDrive extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick stick;
    PID TurnPID;

    public JoystickDrive() {
        super("JoystickDrive");
        requires(driveTrain);

//        leftStick = OI.getLeftDriveStick();
//        rightStick = OI.getRightDriveStick();
//        stick = OI.getStick();
    }

    protected void initialize() {
        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.D);
        driveTrain.setPID(p,i,d);

        if (Robot.getTalonMode() != driveTrain.getCurrentControlMode()) {
            driveTrain.setCurrentControlMode(Robot.getTalonMode());
        }

        //  TurnPID = new PID(.008, 0, 0.1, .2, -.2);
        TurnPID = new PID(Constants.DriveTrain.Ptta, Constants.DriveTrain.Itta, Constants.DriveTrain.Dtta, .3, -.3, 0.01);

    }

    /**
     * Called repeatedly while the command is running
     */
    protected void execute() {
/*        DriveTrain.DriveMode mode = Robot.getDriveMode(); //Get drive mode from SmartDashboard
        switch (mode) {
            case TANK:
                driveTrain.tankDrive(OI.manager.getLeftDriveY(), OI.manager.getRightDriveY());
                break;
            case ARCADE_1_JOYSTICK:*/


                if (OI.manager.getButtonRaw(LogitechF310.Buttons.LB)) {
                    fieldDrive();
                }
                else{
                    driveTrain.arcadeDrive(OI.manager.getRightDriveY(), OI.manager.getRightDriveX());
                }
 /*               break;
            case ARCADE_2_JOYSTICK:
                driveTrain.arcadeDrive(OI.manager.getRightDriveY(), OI.manager.getLeftDriveX());
                break;
        }*/
    }

    /**
     * Returns whether or not the command is finished
     * @return whether or not the command is finished
     */
    @Override
    protected boolean isFinished() {
        return false;
    }


    // **********************************************
    // Uses the left joystick to indicate direction the robot should point (DriveAngle).
    // How far you push the left stick controls how fast the robot will turn (AngleSpeed).
    // The right Y joystick indicates how fast the robot should drive.
    private void fieldDrive(){
        double p = SmartDashboard.getNumber("turn p", Constants.DriveTrain.Ptta);
        double i = SmartDashboard.getNumber("turn i", Constants.DriveTrain.Itta);
        double d = SmartDashboard.getNumber("turn d", Constants.DriveTrain.Dtta);

        TurnPID.SetPID(p,i,d);

        double LeftX = OI.manager.getLeftDriveX();
        double LeftY = OI.manager.getLeftDriveY();
        double DriveAngle = Math.atan2(LeftX,LeftY)*57.296;
        double RobotAngle = AngleUtilities.formatAngle(Robot.ahrs.getYaw());
        double AngleError = AngleUtilities.angle_diff(DriveAngle, RobotAngle);
        double AngleSpeed = sqrt(LeftX*LeftX + LeftY*LeftY);

        double AnglePID = TurnPID.Compute(AngleError);

        System.out.print(AngleError);
        System.out.print(", ");
        System.out.print(AngleSpeed);
        System.out.print(" ");
        System.out.println();

        double speed = OI.manager.getRightDriveY() / 2;
        driveTrain.setSpeedsPercent(speed + AnglePID*AngleSpeed, speed - AnglePID*AngleSpeed);
    }
}
