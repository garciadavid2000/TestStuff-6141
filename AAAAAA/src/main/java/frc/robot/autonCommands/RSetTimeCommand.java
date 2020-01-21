package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotMain.Robot;

/**
 * this command is never used, but can be used to rotate the robot without gyro.
 * Instead of using gyro to find target angle, you may use time and the turn rate. 
 */
public class RSetTimeCommand extends Command {
	
	private final double MAX_ROTATE_SPEED = 0.2;
	
	private double timeLimit;
	private double direction;
	
    //	direction is either 1, or -1. ---> left or right.  
    
    public RSetTimeCommand(double time, double dir) {

    	requires(Robot.sysController.drive);
    	this.timeLimit = time;
        this.direction = dir;
        //DIRECTION IS PLUS OR MINUS 1.
        
    }
    
    protected void initialize() {

        setTimeout(timeLimit);
        
    }

    protected void execute() {

        Robot.sysController.drive.setSpeed(MAX_ROTATE_SPEED * this.direction * -1, MAX_ROTATE_SPEED * this.direction);
        
    }

    protected boolean isFinished() {

        return isTimedOut();
        
    }
    
    protected void end() {
    	Robot.sysController.drive.stopDT();
    }
    
}
