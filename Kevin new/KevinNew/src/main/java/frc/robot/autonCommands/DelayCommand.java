package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Mostly used in the autonomous code, adds a 4 second delay (or variable delay) 
 * as seen in the autonomous class conditional statement.
 * Basically is a " do nothing " command for a set time.
 * 
 */
public class DelayCommand extends Command {

	private double timeLimit;
	
    public DelayCommand(double time) {

        super();
        this.timeLimit = time;
        
    }

    protected void initialize() {

        setTimeout(timeLimit);
        
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
}
