package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RampInputSpeed;
import frc.robot.robotMain.Robot;

public class ROnHeadingCommand extends Command {
	
	private final double MAX_ROTATE_SPEED = 0.4;

	private RampInputSpeed leftRamp = new RampInputSpeed();
	private RampInputSpeed rightRamp = new RampInputSpeed();

	private double heading;
	private double turn;

  public ROnHeadingCommand(double target) {

    requires(Robot.sysController.drive);
		this.heading = target;
		leftRamp.setMaxCPS(0.5);
		rightRamp.setMaxCPS(0.5);
		
  }

  protected void execute() {  

		double angleError = Robot.sysController.g1.getAngleError(heading);
		
		if (Math.abs(angleError) > 30.0) {

			turn = MAX_ROTATE_SPEED * angleError / Math.abs(angleError);
				
		} else {
	
			turn = 0.2 * (angleError / Math.abs(angleError));
				
		}

		double leftSpeed = turn;
		double rightSpeed = -turn;
	
		if (leftSpeed > 1.0) leftSpeed = 1.0;
		if (leftSpeed < -1.0) leftSpeed = -1.0;
		if (rightSpeed > 1.0) rightSpeed = 1.0;
		if (rightSpeed < -1.0) rightSpeed = -1.0;
	
		leftSpeed = leftRamp.rampSpeed(leftSpeed);
		rightSpeed = rightRamp.rampSpeed(rightSpeed);
		  
		Robot.sysController.drive.setSpeed(leftSpeed, rightSpeed);
		
		}
		
  protected boolean isFinished() {

		return (Math.abs(Robot.sysController.g1.getAngleError(heading)) < 5.0);
		
  }
    
  protected void end() {

		 Robot.sysController.drive.stopDT();
		 
  }
    
}
