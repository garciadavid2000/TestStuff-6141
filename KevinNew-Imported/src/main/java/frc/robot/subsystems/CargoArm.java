package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArmCommand;
import frc.robot.robotMain.RobotMap;

public class CargoArm extends Subsystem {

    private Spark mainAxisMotor;

    public void initDefaultCommand() {
      
        setDefaultCommand(new ArmCommand());

    }
    
    public CargoArm () {

        mainAxisMotor = new Spark(RobotMap.MAIN_AXIS_MOTOR_PWM_PORT);

    }

    public void setArmRotateSpeed(double speed) {

        mainAxisMotor.set(speed);

    }

    public void stopMainArm() {

        mainAxisMotor.stopMotor();

    }


    public void updateSD() {

        SmartDashboard.putNumber("Main Arm Power", mainAxisMotor.getSpeed());

    }

}
