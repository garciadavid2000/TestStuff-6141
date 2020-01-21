/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutonomousDistance extends Command {
  double dist;
  int i = 0;
  double tolerance = 1;
  // velocidade do autonomo
  double vel = 0.7;
  public AutonomousDistance(double distance) {
    requires(Robot.m_drive);
    dist = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drive.enc.reset();
    Robot.m_drive.navX.reset();
    Robot.m_drive.resetVirtualYaw();
    Robot.m_drive.resetVirtualEncoder();
    Robot.m_drive.setSetpoint(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // verificação para direcionamento do robô autonomo 
    if (i == 0) {
      Robot.m_drive.resetVirtualYaw();
      Robot.m_drive.resetVirtualEncoder();
      i++;
    }
    if(Robot.m_drive.getVirtualEncoder() > dist)
    Robot.m_drive.arcadeDrive(vel, Robot.m_drive.pidOutput);
    else if(Robot.m_drive.getVirtualEncoder() <= dist) Robot.m_drive.arcadeDrive(-vel, Robot.m_drive.pidOutput);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return approximatelyEqual(dist, (float) Robot.m_drive.getVirtualEncoder(), (float) tolerance);
  }

  public boolean approximatelyEqual(double desiredValue, float actualValue, float tolerancePercentage) {
    float diff = (float) (Math.abs(desiredValue) - Math.abs(actualValue));                
    float tolerance = (float) (tolerancePercentage / 100 * desiredValue);
    return diff < tolerance;                                   
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    int aux = (int) SmartDashboard.getNumber("fim", 0);
    SmartDashboard.putNumber("fim",aux+1);
    Robot.m_drive.enc.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
  }
}
