/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Class to deal with the decoration LEDs for the robot.
 * Classe para lidar com os LEDs de decoração do robô.
 */
public class Leds extends Subsystem {
  /**
   * {@link DigitalOutput} para a fita de LED verde.
   * {@link DigitalOutput} for the green LED tape.
   */
  public Solenoid fitaVerde;
  /**
   * {@link DigitalOutput} para a fita de LED verde.
   * {@link DigitalOutput} for the green LED tape.
   */
  public Solenoid fitaBranca;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Construtor padrão.
   * Default constructor.
   */
  public Leds() {
    fitaVerde = new Solenoid(RobotMap.GREEN_LED_TAPE);
    fitaBranca = new Solenoid(RobotMap.WHITE_LED_TAPE);
  }

  public void GreenTapeOn(){
    fitaVerde.set(true);
  }
  
  public void GreenTapeOff(){
    fitaVerde.set(false);
  }

  public void WhiteTapeOn(){
    fitaBranca.set(true);
  }
  
  public void WhiteTapeOff(){
    fitaBranca.set(false);
  }

}
