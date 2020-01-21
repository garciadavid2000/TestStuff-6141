package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.IntakeCommand;
import frc.robot.robotMain.RobotMap;

public class Intake extends Subsystem {

    private Spark intakeBallMotor;
    private double intakeMotorSpeed;

    public void initDefaultCommand() {
      
        setDefaultCommand(new IntakeCommand());

    }
    
    public Intake () {

        intakeBallMotor = new Spark(RobotMap.CARGO_INTAKE_MOTOR_PWM_PORT);
        intakeMotorSpeed = 0;

    }

    public void setCargoIntakeSpeed(double speed) {

        intakeBallMotor.set(intakeMotorSpeed);

    }

    public void stopCargoIntake() {

        intakeBallMotor.stopMotor();

    }

    public void updateSD() {

        SmartDashboard.putNumber("Intake Power", intakeBallMotor.getSpeed());

    }

}
