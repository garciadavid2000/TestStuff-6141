package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotMain.Robot;

public class LimeLightCommand extends Command {

  private boolean LimelightHasValidTarget = false;
  private double LimelightDriveVal = 0.0;
  private double LimelightSteerVal = 0.0;
  
  public LimeLightCommand() {

    super();
    requires(Robot.sysController.drive);

  }
  
  @Override
  protected void initialize() {
  }
  
  @Override
  protected void execute() {

    if (Robot.oi.getButton5()) {

      Update_Limelight_Tracking();

      if (LimelightHasValidTarget) {

        
        Robot.sysController.drive.setSpeed(LimelightDriveVal, LimelightSteerVal);

      }
    }
  } 

  @Override
  protected boolean isFinished() {

    return false;

  }

  @Override
  protected void end() {

    Robot.sysController.drive.stopDT();

  }

  @Override
  protected void interrupted() {
    // end();
  }

  public void Update_Limelight_Tracking() {


    //NOTE, THESE K-CONSTANTS MUST BE TUNED.
    
    final double STEER_K = 0.03; // how hard to turn toward the target, same idea as gyroTurnConst...
    final double DRIVE_K = 0.26; // how hard to drive forward toward the target
    final double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.5; // speed limit so we don't drive too fast

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    //target match, based on pipeline, <1.0 no target acquired.

    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    //x (horizontal) offset

    // double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    // note: ty is unessecery and unused in this case, vision targets are always in same spots.

    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    //calculated target area, bsaed on target param.

    if (tv < 1.0) { //if no target, don't do anything.

      LimelightHasValidTarget = false;
      LimelightDriveVal = 0.0;
      LimelightSteerVal = 0.0;
      return; //skip rest of method.

    }

    LimelightHasValidTarget = true;

    double steerCMDVal = tx * STEER_K;  //Proportionally steers based on steer const and deltaX.
    LimelightSteerVal = steerCMDVal;

    // try to drive forward until the target area reaches our desired area
    // when ta is reached, the area of target will be large.
    // when far away, calculated target area is small.
    
    double driveCMDVal = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

    //limit max linear speed.    
    if (driveCMDVal > MAX_DRIVE) {

      driveCMDVal = MAX_DRIVE;

    }

    LimelightDriveVal = driveCMDVal;
      
    }
}