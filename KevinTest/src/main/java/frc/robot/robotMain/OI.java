package frc.robot.robotMain;

import frc.robot.robotMain.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
  
//OI CLASS CONSTRUCTOR
  public OI() {

    opCancelButton.whenPressed(new CancelCommand());
    drCancelButton.whenPressed(new CancelCommand());

    // NOTE: no gyro reset command is created yet.
  }

//DRIVER I/O USED, DECLARING DRIVER OBJECTS
  //NOTE: Toggle Class is custom written to create "toggles" that stay on/off until repressed. 
  //See "Toggle" class in robotMain package.

private Joystick driver	= new Joystick(RobotMap.JOYSTICK_DRIVER);

  private Toggle  driveReverse = new Toggle(driver, RobotMap.DRIVER_TRIGGER, false);
  private Toggle  drivePrecise = new Toggle(driver, RobotMap.TOGGLE_2, false);
  private Toggle shiftGears = new Toggle(driver, RobotMap.TOGGLE_6, false);
  private Toggle  driveStraight	= new Toggle(driver, RobotMap.TOGGLE_7, false);

  private Button  drCancelButton = new JoystickButton(driver, RobotMap.TOGGLE_12); 

//OPERATOR I/O USED, DECLARING OPERATOR OBJECTS

  private Joystick operator = new Joystick(RobotMap.JOYSTICK_OPERATOR);

  private Button opCancelButton = new JoystickButton(driver, RobotMap.BUTTON_Y); //CANCEL ALL OP.

//DRIVER JOYSTICK METHODS

  // Axis 1: y (F/B tilt), Axis 2: z (Twist), Axis 3: x (L/R tilt), Axis 4: Throttle

  public double getYAxis() { return driver.getRawAxis(1); } //GET LINEAR SPEED
  public double getZAxis() { return driver.getRawAxis(2); } //GET RATE OF TURN

//DRIVER TOGGLE METHODS

  public boolean getButton5() { return driver.getRawButton(RobotMap.TOGGLE_5); }

  public boolean isPreciseDrive() { return drivePrecise.getToggle(); } //PRECISION DRIVE ACTIVATION
  public boolean isReverseDrive() { return driveReverse.getToggle(); } //SWITCH DRIVE DIRECTION
  public boolean isStraightDrive() { return driveStraight.getToggle(); } //STRAIGHT DRIVE ACTIVATION
  public boolean isShiftGears() { return shiftGears.getToggle(); } //SHIFT GEARS; SWITCH GEAR ON DT.

//OPERATOR METHODS, NOTE: Not toggles, therefore no need to create an objects for operator...

  public double getRY() { return operator.getRawAxis(RobotMap.RS_Y_AXIS); } //CARGO ARM MOVEMENT
  public double getLY() { return operator.getRawAxis(RobotMap.LS_Y_AXIS); } //HATCH ARM MOVEMENT

  public double getRT() { return operator.getRawAxis(RobotMap.RT_AXIS); } //BALL IN
  public double getLT() { return operator.getRawAxis(RobotMap.LT_AXIS); } //BALL OUT

  public boolean getLBumper() { return operator.getRawButton(RobotMap.BUTTON_LB); } //PNEUMATICS OUT
  public boolean getRBumper() { return operator.getRawButton(RobotMap.BUTTON_RB); } //PNEUMATICS IN
  
  public boolean getAButton() { return operator.getRawButton(RobotMap.BUTTON_A); } //SLOW CARGO ARM
  public boolean getXButton() { return operator.getRawButton(RobotMap.BUTTON_X); } //HOLD HATCH ARM
  
//SHOW INFO ON SD, FOR TESTING ONLY

  public void updateSD() {

    //DISPLAY DRIVER DATA
    // SmartDashboard.putNumber("LOGI YAXIS", getYAxis());
    // SmartDashboard.putNumber("LOGI XAXIS", getZAxis());

    // SmartDashboard.putBoolean("LOGI TRIGGER", getTrigger());

    SmartDashboard.putBoolean("Straight Drive", isStraightDrive());
    SmartDashboard.putBoolean("Reverse Drive", isReverseDrive());
    SmartDashboard.putBoolean("Precise Drive", isPreciseDrive());
    SmartDashboard.putBoolean("DriveGear", isShiftGears());

    // SmartDashboard.putBoolean("Moderate Drive", isModerateDrive());
    // SmartDashboard.putBoolean("Slow Turn", isSlowDrive());

    // DISPLAY OPERATOR DATA
    // SmartDashboard.putNumber("RSY INPUT", getRY());
    // SmartDashboard.putNumber("LSY INPUT", getLY());

    // SmartDashboard.putNumber("RT INPUT", getRT());
    // SmartDashboard.putNumber("LT INPUT", getLT());

    // SmartDashboard.putBoolean("A BUTTON", getAButton());
    // SmartDashboard.putBoolean("X BUTTON", getXButton());
    // SmartDashboard.putBoolean("L BUMPER", getLBumper());
    // SmartDashboard.putBoolean("R BUMPER", getRBumper());

  }

  public void resetToggles() {

    driveReverse.setToggle(false);
    drivePrecise.setToggle(false);
    driveStraight.setToggle(false);
    shiftGears.setToggle(false);

  }

}
