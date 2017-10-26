package org.team639.robot.commands.Gear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Constants;
import org.team639.robot.Robot;

public class GearAcquisitionCycle extends Command {

    private Timer time;
    private int state;
    private boolean done;

    public GearAcquisitionCycle() {
        super("GearAcquisitionCycle");
        System.out.println(Robot.gearAcquisition);
        requires(Robot.gearAcquisition);

        time = new Timer();
    }

    /**
     * Called when the command begins
     */
    protected void initialize() {
        time.reset();
        state = 0;
        done = false;
    }

    /**
     * Called repeatedly while the command runs
     */
    protected void execute() {
        switch (state) {
            case 0: //Opening claw
                if (!Robot.gearAcquisition.isOpen()) {
                    Robot.gearAcquisition.open();
                    time.start();
                }
                if (Robot.gearAcquisition.isOpen() && time.hasPeriodPassed(Constants.OPEN_CLAW_TIME)) {
                    state = 1;
                    time.stop();
                    time.reset();
                }
                break;
            case 1: //Lowering claw
                if (!Robot.gearAcquisition.isLowered()) {
                    Robot.gearAcquisition.lower();
                    time.start();
                }
                if (Robot.gearAcquisition.isLowered() && time.hasPeriodPassed(Constants.LOWER_CLAW_TIME)) {
                    state = 2;
                    time.stop();
                    time.reset();
                }
                break;
            case 2: //Waiting for gear
                if (Robot.gearAcquisition.gearDetected()) {
                    state = 3;
                }
                break;
            case 3: //closing on gear
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
            case 4: //Raising claw
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
        end();
    }

    /**
     * Called when the command ends
     */
    protected void end() {
        // This should be handled by GearPickup as a whenReleased on the same button
//        Robot.gearAcquisition.raise();
//        Robot.gearAcquisition.close();
    }
}
