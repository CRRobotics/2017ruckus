package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

public class SetTurnPID extends Command {

    public SetTurnPID(){

         DriveTrain driveTrain = Robot.getDriveTrain();

        double p = .02;
        double i= .001;
        double d= .02;

        SmartDashboard.getNumber("turn p", p);
        SmartDashboard.getNumber("turn i", i);
        SmartDashboard.getNumber("turn d", d);
        driveTrain.SetTurnPID(p, i, d);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

}
