/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Code for 
 */
public class GrabHatch extends InstantCommand {

  public GrabHatch() {
    super();
    requires(Robot.m_tower);
  }

  @Override
  protected void initialize() {

    Thread thread = new Thread(()->{
      Robot.m_tower.tower.set(ControlMode.PercentOutput, 1);
    });
    thread.start();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      thread.interrupt();
      e.printStackTrace();
    }
    thread.interrupt();

  }

}
