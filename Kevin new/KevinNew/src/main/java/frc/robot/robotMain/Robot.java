package frc.robot.robotMain;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonCommands.AutoOptionsEnum;
import frc.robot.autonCommands.AutonMain;
import frc.robot.subsystems.SystemController;

public class Robot extends TimedRobot {
  
  public static OI oi; //create instance of OI class.
  AutonMain autonomousCommand; //create a blank commmand. 

  public static SystemController sysController = new SystemController();

  SendableChooser<AutoOptionsEnum> chooser = new SendableChooser<AutoOptionsEnum>();
  SendableChooser<AutoOptionsEnum> withDelay = new SendableChooser<AutoOptionsEnum>();
  SendableChooser<AutoOptionsEnum> withGyro = new SendableChooser<AutoOptionsEnum>();
  
  @Override
  public void robotInit() {

    oi = new OI();

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
    camera.setFPS(30);
    
    chooser.setDefaultOption("DS", AutoOptionsEnum.driveStraight);
    chooser.addOption("L1", AutoOptionsEnum.L1);
    chooser.addOption("L2", AutoOptionsEnum.L2);
    chooser.addOption("R1", AutoOptionsEnum.R1);
    chooser.addOption("R2", AutoOptionsEnum.R2);
    chooser.addOption("CL", AutoOptionsEnum.CL);
    chooser.addOption("CR", AutoOptionsEnum.CR);

    withDelay.setDefaultOption("NO DELAY", AutoOptionsEnum.NO_DELAY);
    withDelay.addOption("ADD 3S DELAY", AutoOptionsEnum.DELAY);

    withGyro.setDefaultOption("WITH GYRO", AutoOptionsEnum.WITHGYRO);
    withGyro.addOption("NO GYRO", AutoOptionsEnum.DELAY);

    SmartDashboard.putData("AUTO MODE:", chooser);
    SmartDashboard.putData("ADD DELAY?", withDelay);
    SmartDashboard.putData("WITH GYRO?", withGyro);

    sysController.g1.calibrate();
    sysController.g1.reset();

    autonomousCommand = new AutonMain(chooser.getSelected(), 
    withDelay.getSelected(), 
    withGyro.getSelected());

  }

  private void updateDash() {

    oi.updateSD();
    sysController.updateAllSD();

  }

  @Override
  public void robotPeriodic() {

    updateDash();

  }

  @Override
  public void disabledInit() {

    sysController.airSystem.pistonOff();

  }

  @Override
  public void disabledPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void autonomousInit() {

    //init command above, set command here.

    if (autonomousCommand != null) {

      autonomousCommand.start();

    }

  }

  @Override
  public void autonomousPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {

    if (autonomousCommand != null) {

      autonomousCommand.cancel();

    }
    
  }

  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void testPeriodic() {
  }
}
