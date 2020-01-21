package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.robotMain.Robot;

/**
 *  Code for commands that delete all commands.
 *  When run, the execute() method will:
 *  remove all commands in queue from Scheduler.
 * 
 *  Calls no other methods.
 * 
 */

public class CancelCommand extends Command {

  public CancelCommand() {
    
    super();
    
  }
  
  @Override
  protected void initialize() {  
  }

  @Override
  protected void execute() {  

    Scheduler.getInstance().removeAll();
    Robot.oi.resetToggles();

  }

  protected boolean isFinished() {  

    return true;

  }

}
