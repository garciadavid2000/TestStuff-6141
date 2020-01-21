package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RampInputSpeed;
import frc.robot.robotMain.Robot;

public class DOnHeadingCommmand extends Command {
	
	private double heading;
	private double speed;
	private double time;

	private RampInputSpeed leftRamp = new RampInputSpeed();
	private RampInputSpeed rightRamp = new RampInputSpeed();

	private final double STRAIGHT_DRIVE_TURN_RATE = 0.005;
	
	public DOnHeadingCommmand(double heading, double speed, double time) {
		
		super();
		requires(Robot.sysController.drive);
		this.heading = heading;
		this.speed = speed;
		this.time = time;


	}
	
	protected void initialize() {
		setTimeout(time);
	}
	
	@Override
	protected void execute() {
			
		double turn = Robot.sysController.g1.getAngleError(heading) 
												* STRAIGHT_DRIVE_TURN_RATE;
		if (turn > 1) turn = 1;
		if (turn < -1) turn = -1;

		double leftSpeed = -speed + turn;
		double rightSpeed = speed - turn;
	
		if (leftSpeed > 1.0) leftSpeed = 1.0;
		if (leftSpeed < -1.0) leftSpeed = -1.0;
		if (rightSpeed > 1.0) rightSpeed = 1.0;
		if (rightSpeed < -1.0) rightSpeed = -1.0;
	
		leftSpeed = leftRamp.rampSpeed(leftSpeed);
		rightSpeed = rightRamp.rampSpeed(rightSpeed);
		  
		Robot.sysController.drive.setSpeed(leftSpeed, rightSpeed);
		
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.sysController.drive.stopDT();
	}

	@Override
    protected void interrupted() {
    }

}
