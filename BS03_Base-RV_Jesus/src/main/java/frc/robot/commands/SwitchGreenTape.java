/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SwitchGreenTape extends InstantCommand {
  /**
   * Add your docs here.
   */
  private boolean isTapeOn;

  public SwitchGreenTape() {
    super();
    requires(Robot.m_Leds);

    //caso ligado no normalmente fechado
    isTapeOn = false;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(isTapeOn) Robot.m_Leds.GreenTapeOff();
    else Robot.m_Leds.GreenTapeOn();
    isTapeOn = !isTapeOn;
  }

}
