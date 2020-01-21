package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.HatchCommand;
import frc.robot.robotMain.RobotMap;

public class HatchFingers extends Subsystem {

    private Spark hatchArmMotor;

    public void initDefaultCommand() {
        
      setDefaultCommand(new HatchCommand());
  
    }

    public HatchFingers () {

        hatchArmMotor = new Spark(RobotMap.HATCH_AXIS_MOTOR_PWM_PORT);

    }

    public void setFingersSpeed(double speed) {

        hatchArmMotor.set(speed);

    }

    public void stopHArm() {

        hatchArmMotor.stopMotor();

    }

    public void updateSD() {

        SmartDashboard.putNumber("Hatch Arm Power", hatchArmMotor.getSpeed());
        
    }

}
