package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;

public class GearPickup extends Command {

    private Timer time;
    private int state;
    private boolean done;

    public GearPickup() {
        super("GearPickup");
        requires(Robot.gearAcquisition);

        time = new Timer();
    }

    /**
     * Called when the command begins
     */
    protected void initialize() {
        time.reset();
        state = 0;
        if (!Robot.gearAcquisition.isOpen()) state = 1;
        done = false;
        if (!Robot.gearAcquisition.isLowered()) done = true;
    }

    /**
     * Called repeatedly while the command runs
     */
    protected void execute() {
        switch (state) {
            case 0: //closing on gear
                if (Robot.gearAcquisition.isOpen()) {
                    Robot.gearAcquisition.close();
                    time.start();
                }
                if (!Robot.gearAcquisition.isOpen() && time.hasPeriodPassed(Constants.OPEN_CLAW_TIME)) {
                    state = 4;
                    time.stop();
                    time.reset();
                }
                break;
            case 1: //Raising claw
                if (Robot.gearAcquisition.isLowered()) {
                    Robot.gearAcquisition.raise();
                    time.start();
                }
                if (!Robot.gearAcquisition.isLowered() && time.hasPeriodPassed(Constants.LOWER_CLAW_TIME)) {
                    state = 5;
                    done = true;
                    time.stop();
                    time.reset();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Returns whether or not the command is finished
     * @return Whether or not the command is finished
     */
    @Override
    protected boolean isFinished() {
        return done;
    }

    /**
     * Called if the command is interrupted
     */
    protected void interrupted() {

    }

    /**
     * Called when the command ends
     */
    protected void end() {
        Robot.gearAcquisition.raise();
        Robot.gearAcquisition.close();
    }
}
