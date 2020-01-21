package frc.robot.autonCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotMain.Robot;

/**
 *  Code for commands on the hatch side of robot.
 *  When run, the execute() method 
 *  will run based on parameters given in constructor.
 *  the pneumatics and the arm motor. 
 * 
 *  Calls the setSpeed method and the pistonIn/Out methods
 *  from the HatchArm subsystem in subsystems package.
 *  Uses fingers object in SystemController class to access these methods.
 * 
 */

public class AutoHCommand extends Command {

  final private double maxArmSpeed = 0.2;
  private double hatchArmSpeed, time;
  private boolean inOut;

  public AutoHCommand(double direction, double seconds) {

    super();
    requires(Robot.sysController.fingers);
    requires(Robot.sysController.airSystem);

    this.hatchArmSpeed = direction * maxArmSpeed;
    this.time = seconds;

  }
  
  public AutoHCommand(Boolean pistonInOutBoolean, double pistonTime) {

    super();
    requires(Robot.sysController.fingers);
    requires(Robot.sysController.airSystem);
    this.time = pistonTime;

  }

  @Override
  protected void initialize() {

    Robot.sysController.airSystem.hatchPiston(false);
    setTimeout(time);
    hatchArmSpeed = 0;

  }

  @Override
  protected void execute() {

    Robot.sysController.fingers.setFingersSpeed(hatchArmSpeed);

    if (inOut) {

        Robot.sysController.airSystem.hatchPiston(true);

    } else {

      Robot.sysController.airSystem.hatchPiston(false);

    }

  }

  @Override
  protected boolean isFinished() {

    return isTimedOut();

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.sysController.fingers.stopHArm();
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {

    end();
    
  }

}
