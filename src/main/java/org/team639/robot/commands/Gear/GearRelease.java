package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;

public class GearRelease extends Command {

    private boolean done;
    private Timer time;

    public GearRelease() {
        time = new Timer();
    }

    @Override
    protected void initialize() {
        done = false;
    }

    @Override
    protected void execute() {
//        if (!Robot.gearAcquisition.isOpen()) {
            Robot.gearAcquisition.open();
            time.start();
//        }
        if (Robot.gearAcquisition.isOpen() && time.hasPeriodPassed(Constants.OPEN_CLAW_TIME)) {
            time.stop();
            time.reset();
        }
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {
        Robot.gearAcquisition.close();
    }
}
