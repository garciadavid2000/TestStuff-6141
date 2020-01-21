
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MoveTower extends Command {

  /**
   * Default constructor
   * Construtor padrão
   */
  public MoveTower() {
    requires(Robot.m_tower);
  }

  /**
   * Called when the Command is initialized for the first time.
   * Chamado quando o comando é inicializado pela primeira vez.
   */
  @Override
  protected void initialize() {
  }

  /**
   * Chamado repetidamente enquanto o comando é executado. Aqui, ele serve para realizar o movimento da
   * garra e disponibizá-lo para análise na Shuffleboard.
   * Called repeatedly while the command is running. Here, it realizes the claw movement and puts it up
   * for analysis in the Shuffleboard.
   */
  @Override
  protected void execute() {
    double vel = Robot.m_oi.driverController.getRawAxis(3)-Robot.m_oi.driverController.getRawAxis(2);
    
    SmartDashboard.putBoolean("Subindo Garra",vel > 0);
    SmartDashboard.putBoolean("Descendo Garra",vel < 0);

    vel +=0.07;
    Robot.m_tower.move(vel);
  }

  /**
   * Verifica se o comando já terminou ou não.
   * Verifies if the command is or not finished.
   */
  @Override
  protected boolean isFinished() {
    return false;
  }

  /**
   * Chamado quando a função {@link isFinished()} retorna verdadeiro.
   * Called when the function {@link isFinished()} returns true.
   */
  @Override
  protected void end() {
  }

  /**
   * Chamado quando outro comando que requer os mesmos subsistemas é requisitado.
   * Called when another command that needs the same subsystems is scheduled.
   */
  @Override
  protected void interrupted() {
  }
}
