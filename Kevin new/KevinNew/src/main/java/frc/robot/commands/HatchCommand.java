package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotMain.Robot;

/**
 *  Code for commands on the hatch side of robot.
 *  When run, the execute() method 
 *  will get left JS y-axis and check for Y-button to operate 
 *  the pneumatics and the arm motor. 
 * 
 *  Calls the setSpeed method and the pistonIn/Out methods
 *  from the HatchArm subsystem in subsystems package.
 *  Uses secondArm object in SystemController class to access these methods.
 * 
 */

public class HatchCommand extends Command {

  final private double maxFingerSpeed = 0.4;
  final private double minFingerSpeed = -0.4;

  private double fingerSpeed;

  public HatchCommand() {

    super();
    requires(Robot.sysController.fingers);
    requires(Robot.sysController.airSystem);

  }

  @Override
  protected void initialize() {

    Robot.sysController.airSystem.hatchPiston(false);

    fingerSpeed = 0;

  }

  @Override
  protected void execute() {

    fingerSpeed = Robot.oi.getLY();

    if (Math.abs(fingerSpeed) > 0.2) {

      if (fingerSpeed > maxFingerSpeed) {

        fingerSpeed = maxFingerSpeed;
  
      } else if (fingerSpeed < minFingerSpeed) {
  
        fingerSpeed = minFingerSpeed;
  
      } else {

        fingerSpeed = Robot.oi.getRY();
        
      }

    } else {

      fingerSpeed = 0;
      
    }

    Robot.sysController.fingers.setFingersSpeed(fingerSpeed);

    if (Robot.oi.getLBumper()) {

      Robot.sysController.airSystem.hatchPiston(true);

    } else if (Robot.oi.getRBumper()){

      Robot.sysController.airSystem.hatchPiston(false);

    }

  }

  @Override
  protected boolean isFinished() {

    return false;

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
