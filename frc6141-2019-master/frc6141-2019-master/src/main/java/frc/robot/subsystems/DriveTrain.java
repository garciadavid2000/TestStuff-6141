package frc.robot.subsystems;

import frc.robot.commands.DriveCommand;
import frc.robot.robotMain.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DriveTrain extends Subsystem {

    private VictorSP leftOne;
	private VictorSP leftTwo;
	private PWMVictorSPX leftThree;
    private VictorSP rightOne;
	private VictorSP rightTwo;
	private PWMVictorSPX rightThree;

    public void initDefaultCommand() {
			
		setDefaultCommand(new DriveCommand());
				
    }

    public DriveTrain() {
			
		leftOne = new VictorSP(RobotMap.LEFT_MOTOR_ONE_PWM_PORT);
		leftTwo = new VictorSP(RobotMap.LEFT_MOTOR_TWO_PWM_PORT);
		leftThree = new PWMVictorSPX(RobotMap.LEFT_MOTOR_THREE_PWM_PORT);

    	rightOne = new VictorSP(RobotMap.RIGHT_MOTOR_ONE_PWM_PORT);
		rightTwo = new VictorSP(RobotMap.RIGHT_MOTOR_TWO_PWM_PORT);
		rightThree = new PWMVictorSPX(RobotMap.RIGHT_MOTOR_THREE_PWM_PORT);	

    }
    
    public void setSpeed(double leftSpeed, double rightSpeed) {
		//actual method that sets motor speeds.

    	leftOne.set(leftSpeed);
		leftTwo.set(leftSpeed);
		leftThree.set(leftSpeed);
    	rightOne.set(rightSpeed);
		rightTwo.set(rightSpeed);
		rightThree.set(rightSpeed);

    }
    
    public void stopDT() {

    	leftOne.stopMotor();
		leftTwo.stopMotor();
		leftThree.stopMotor();
    	rightOne.stopMotor();
		rightTwo.stopMotor();
		rightThree.stopMotor();
		
    }
    
    public void updateSD() {

    	SmartDashboard.putNumber("Drive LeftSpeed", leftOne.getSpeed());
		SmartDashboard.putNumber("Drive RightSpeed", rightOne.getSpeed());
		
    }
}

