/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
  // instantiate motor controllers
  public WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(RobotMap.leftMotorPort1);
  public WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(RobotMap.leftMotorPort2);
  public WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(RobotMap.rightMotorPort1);
  public WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(RobotMap.rightMotorPort2);



  //instantiate DifferentialDrive object
  public DifferentialDrive drive = new DifferentialDrive(leftMotor1, rightMotor1); 


  //constructor function
  public DriveSubsystem(){
    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);

  }





  //add manualDrive() method
  public void manualDrive(double move, double turn){


    // setting max speed
    if (move > 0.6 ){
      move = 0.6;
    }


    drive.arcadeDrive(move, turn);;

  }
 
 
 
 
 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualDriveCommand());
  }
}
