package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.sensors.GyroClass;

/**
 * Class used to create objects of subsyetem classes, 
 * to enable use of the methods inside each subsystem.
 * EG: check hatchCommand, when calling the rotateArm command, 
 * it calls "secondArm", not HatchArm.
 * Subsystems are not static, therefore objects must be created to access methods.
 * Note that an instance of this class is 
 * created inside the Robot (VM auto-run) class. 
 * 
 */

public class SystemController extends Subsystem {
	
	public DriveTrain drive;
	public CargoArm arm;
	public HatchFingers fingers;
	public Pneumatics airSystem;
	public GyroClass g1;
	public Intake intakeWheels;

	// ^^^declare objects

	public SystemController() { //Use constructor to create objects of all subsystems.

		drive = new DriveTrain();
		arm = new CargoArm();
		fingers = new HatchFingers();
		airSystem = new Pneumatics();
		g1 = new GyroClass();
		intakeWheels = new Intake();

	}
	
	public void updateAllSD() { //update SD for all subsystems.

		drive.updateSD();
		arm.updateSD();
		fingers.updateSD();
		airSystem.updateSD();
		intakeWheels.updateSD();
		g1.updateSD();
		
	}

	@Override
	protected void initDefaultCommand() {

	}
        
}

