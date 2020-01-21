/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class AutonomousRoutine extends CommandGroup {
  /**
   * Add your docs here.
   */

  double unidadeOca;
  public AutonomousRoutine() {
    
    int Alfa = 90;
    int A=200,B=200,C=150;


    //Primeira Parte
    
    addSequential(new AutonomousDistance(A));
    
    addSequential(new AutonomousTurn(Alfa));
    addSequential(new AutonomousDistance(B));
    addSequential(new AutonomousTurn(-Alfa));
    addSequential(new AutonomousDistance(C));
    addSequential(new AutonomousTurn(-90));
    
    
    //Segunda Parte
    addParallel(new AutonomousTower(0.4, false));
    addSequential(new AutonomousTapeTurn(2));

    addSequential(new TimedDistance(1, true));
    addSequential(new AutonomousTower(0.4, true));
    addSequential(new TimedDistance(1, false));
    
  }

}
