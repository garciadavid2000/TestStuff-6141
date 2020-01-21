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
public class AutonomousTapeTurn extends TimedCommand {
  /**
   * Add your docs here.
   */
  private final double CONST_CONVERSION = -0.2;
  private double sp;
  int i = 0;
  public AutonomousTapeTurn(double timeout) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drive.navX.reset();
    sp = Robot.entryDiff.getDouble(0.0) * CONST_CONVERSION;
    Robot.m_drive.setSetpoint(sp);
    Robot.m_drive.resetVirtualEncoder();
    Robot.m_drive.resetVirtualYaw();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (i == 0) {
      Robot.m_drive.resetVirtualYaw();
      Robot.m_drive.resetVirtualEncoder();
      i++;
    }
    Robot.m_drive.arcadeDrive(0, Robot.m_drive.pidOutput);
  }

  public boolean approximatelyEqual(double desiredValue, float actualValue, float tolerancePercentage) {
    float diff = (float) Math.abs(Math.abs(desiredValue) - Math.abs(actualValue));         
    float tolerance = (float) (tolerancePercentage / 100 * Math.abs(desiredValue));
    return diff < tolerance;                                   
  }

  // Called once after timeout
  @Override
  protected void end() {
    approximatelyEqual(sp, Robot.m_drive.getVirtualYaw(), 2);
    Robot.m_drive.navX.reset();
    Robot.m_drive.enc.reset();
    Robot.m_drive.setSetpoint(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
