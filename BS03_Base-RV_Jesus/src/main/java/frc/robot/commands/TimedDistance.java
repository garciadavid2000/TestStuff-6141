/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class TimedDistance extends TimedCommand {
  /**
   * Add your docs here.
   */

  boolean reverse;

  public TimedDistance(double timeout, boolean reverse) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drive);
    this.reverse = reverse;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drive.navX.reset();
    Robot.m_drive.setSetpoint(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (reverse) {
      Robot.m_drive.arcadeDrive(-0.7, Robot.m_drive.pidOutput);
    } else {
      Robot.m_drive.arcadeDrive(0.7, Robot.m_drive.pidOutput);
    }
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.m_drive.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
