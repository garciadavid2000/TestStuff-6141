package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotMain.Robot;

/**
 *  Code for commands intake wheels for cargo of robot.
 *  When run, the execute() method will:
 *  get values from RT and LR
 *  to operate the cargo intake wheels. 
 * 
 *  Calls the setCargoIntakeSpeed method
 *  from the Intake subsystem in subsystems package.
 *  Uses intakeWheels object in SystemController class to access these methods.
 * 
 */

public class AutoInCommand extends Command {

private final double intakeSpeed = 0.9;
private final double outputSpeed = -0.8;
private final double time = 3;
private boolean inOut;

  public AutoInCommand(boolean direction) {

    super();
    requires(Robot.sysController.intakeWheels);

  }

  @Override
  protected void initialize() {

    setTimeout(time);    

  }

  @Override
  protected void execute() {

    if (inOut) {

        Robot.sysController.intakeWheels.setCargoIntakeSpeed(intakeSpeed);

    } else if (!inOut) {

        Robot.sysController.intakeWheels.setCargoIntakeSpeed(outputSpeed);

    }

  }

  @Override
  protected boolean isFinished() {

    return isTimedOut();

  }

  @Override
  protected void end() {
    
    Robot.sysController.intakeWheels.stopCargoIntake();

  }
  
  @Override
  protected void interrupted() {

    end();

  }
}
