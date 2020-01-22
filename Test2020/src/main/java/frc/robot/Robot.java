/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  public VictorSP leftMotor1 = new VictorSP(0);
  public WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(0);
  public VictorSP leftMotor3 = new VictorSP(2);
  public VictorSP rightMotor1 = new VictorSP(3);
  public VictorSP rightMotor2 = new VictorSP(4);
  public VictorSP rightMotor3 = new VictorSP(7);
  public Spark test1 = new Spark(6);
  public Spark test2 = new Spark(4);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2, leftMotor3);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2, rightMotor3);


  //instantiate DifferentialDrive object
  public DifferentialDrive m_robotDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  //private final Joystick m_stick = new Joystick(0);
  private final XboxController xStick = new XboxController(0);
  Button buttonA = new JoystickButton(xStick, 1);

  

  //private DifferentialDrive outputTest = new DifferentialDrive(test2, test1);
  
  
  private final Timer m_timer = new Timer();

  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
  //   if (m_timer.get() < 2.0) {
  //     m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
  //   } else {
  //     m_robotDrive.stopMotor(); // stop robot
  //   }
  // }

  if (m_timer.get() < 2.0) {
    //test.set(1); // drive forwards half speed
  } else {
    //test.stopMotor(); // stop robot
  }
   }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    
    
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    


    // if(buttonA.get()) {
    //   test.set(1);
    //   xStick.setRumble(RumbleType.kLeftRumble, 1);
    //   xStick.setRumble(RumbleType.kRightRumble, 1);
    // } else {
    //   test.set(0);
    //   xStick.setRumble(RumbleType.kLeftRumble, 0);
    //   xStick.setRumble(RumbleType.kRightRumble, 0);
    //}

    
    // for (int i = 0; i < 1; i++) {
    //   m_timer.reset();
    //   m_timer.start();
    // }

    // if (m_timer.get() < 2.0) {
    //   test.set(1); // drive forwards half speed
    // } else {
    //   test.stopMotor();; // stop robot
    //}
      test1.set(1);
      test2.set(-1);
  
  }
}

