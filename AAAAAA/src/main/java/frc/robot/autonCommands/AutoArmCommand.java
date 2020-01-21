package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotMain.Robot;

/**
 *  Code for commands for cargo arm motor.
 *  When run, the execute() method will:
 *  get right JS y-axis
 *  to operate the cargo arm motor. 
 * 
 *  Calls the setMainArmRotateSpeed method
 *  from the CargoArm subsystem in subsystems package.
 *  Uses arm object in SystemController class to access these methods.
 * 
 */

public class AutoArmCommand extends Command {

  final private double maxArmSpeed = 0.3;
  final private double minArmSpeed = -0.3;
  private double time;
  private boolean direction; //false is down, true is up.

  public AutoArmCommand(boolean upDown, double seconds) {

    super();
    this.time = seconds;

    //NOTE TIME IS NOT TUNED! CAREFUL.

    this.direction = upDown;
    requires(Robot.sysController.arm);

  }

  @Override
  protected void initialize() {

    setTimeout(time);

  }

  @Override
  protected void execute() {

    if (direction) {

      Robot.sysController.arm.setArmRotateSpeed(maxArmSpeed);

    } else if(!direction) {

      Robot.sysController.arm.setArmRotateSpeed(minArmSpeed);

    }
  
  }

  @Override
  protected boolean isFinished() {

    return isTimedOut();

  }

  @Override
  protected void end() {

    Robot.sysController.arm.stopMainArm();

  }
  
  @Override
  protected void interrupted() {

    end();

  }
}
