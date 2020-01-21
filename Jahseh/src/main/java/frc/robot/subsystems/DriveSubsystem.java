/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDriveCommand;;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //instantiate motor controllers
  public VictorSP leftMotor1 = new VictorSP(RobotMap.leftMotorPort1);
  public WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(RobotMap.leftMotorPort2);
  public VictorSP rightMotor1 = new VictorSP(RobotMap.rightMotorPort1);
  public WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(RobotMap.rightMotorPort2);
  public VictorSP leftMotor3 = new VictorSP(RobotMap.leftMotorPort3);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2, leftMotor3);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2);


  //instantiate DifferentialDrive object
  public DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup); 


  //add manualDrive() method
  public void manualDrive(double move, double turn){


    // setting max speed
    if (move > 0.5 ){
      move = 0.5;
    }


    drive.arcadeDrive(move, turn);;

  }
 
 
 
 
 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualDriveCommand());
  }
}
