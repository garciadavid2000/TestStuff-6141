/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

   // Variable Declarations
  double leftSlow = 0.24;
  double rightSlow = -0.24;
  double rotateSpeed = 0.35;
  double rotateSpeedSlow = 0.25;

  // Inputs
  AnalogGyro gyro = new AnalogGyro(0); // ANA Ch. 0
  // *NOTE* the Gyroscope purchased defines a 300 degree full rotation! (not 360!!)

  // Outputs
  VictorSP victorLeft = new VictorSP(0);  // PWM Ch. 0
  VictorSP victorRight = new VictorSP(1); // PWM Ch. 1
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    gyro.reset();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    gyro.reset();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
      if (Math.abs(gyro.getAngle()) <= 3) {
        victorLeft.set(leftSlow - (gyro.getAngle()) / 15);
        victorRight.set(rightSlow - (gyro.getAngle()) / 15);
       } else if (Math.abs(gyro.getAngle()) < 10) {
        if (gyro.getAngle() > 0) {
         victorLeft.set(leftSlow);
         victorRight.set(1.1 * rightSlow);
        } else if (gyro.getAngle() < 0) {
         victorLeft.set(1.1 * leftSlow);
         victorRight.set(rightSlow);
        }
       } else
        if (gyro.getAngle() > 0) {
         while (gyro.getAngle() > 10 && isAutonomous()) {
          victorLeft.set(-rotateSpeed);
          victorRight.set(-rotateSpeed);
         }
        while (gyro.getAngle() > 0 && isAutonomous()) {
         victorLeft.set(-rotateSpeedSlow);
         victorRight.set(-rotateSpeedSlow);
        }
        while (gyro.getAngle() < 0 && isAutonomous()) {
         victorLeft.set(rotateSpeedSlow);
         victorRight.set(rotateSpeedSlow);
        }
       } else {
        while (gyro.getAngle() < -10 && isAutonomous()) {
         victorLeft.set(rotateSpeed);
         victorRight.set(rotateSpeed);
        }
        while (gyro.getAngle() < 0 && isAutonomous()) {
         victorLeft.set(rotateSpeedSlow);
         victorRight.set(rotateSpeedSlow);
        }
        while (gyro.getAngle() > 0 && isAutonomous()) {
         victorLeft.set(-rotateSpeedSlow);
         victorRight.set(-rotateSpeedSlow);
        }
        break;
    }
  }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
