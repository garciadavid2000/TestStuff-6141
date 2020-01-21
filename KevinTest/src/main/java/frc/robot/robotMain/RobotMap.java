package frc.robot.robotMain;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

//PWM NUMBERS ON ROBORIO TO MC's

	public static final int LEFT_MOTOR_ONE_PWM_PORT = 0;
	public static final int LEFT_MOTOR_TWO_PWM_PORT = 1;
	public static final int LEFT_MOTOR_THREE_PWM_PORT = 7;

	public static final int RIGHT_MOTOR_ONE_PWM_PORT = 2;
	public static final int RIGHT_MOTOR_TWO_PWM_PORT = 3;
	public static final int RIGHT_MOTOR_THREE_PWM_PORT = 8;

	public static final int MAIN_AXIS_MOTOR_PWM_PORT = 4;  
	public static final int CARGO_INTAKE_MOTOR_PWM_PORT = 5;
	public static final int HATCH_AXIS_MOTOR_PWM_PORT = 6;
	

  // BELOW, DECLARES CONSTANT INPUT "PORTS" USED ON PHYSICAL CONTROLLER
  // EG. "X" BUTTON MAY CORRESPOND TO THE NUMBER 0, ETC...
  // LS IDENTIFIER IS PHYSICAL BUTTON LABEL, RS IS CORRESPONDING PORT.
  // BELEIVE IT OR NOT, THE BUTTON NUMBER IS THE DIGITAL NUMBER ON LOGI.


//DRIVER CONTROLS (JOYSTICK, TOGGLES, BUTTONS) --> LOGI FLIGHT STICK

	public static final int JOYSTICK_DRIVER = 0; //DRIVER JOYSTICK PORT
	  
	public static final int DRIVER_TRIGGER = 1; //REVERSE DRIVE
	public static final int TOGGLE_2 = 2; //PRECISION DRIVE 
	
	public static final int TOGGLE_5 = 5; //ACTIVATE LIMELIGHT (HOLD DOWN)
	public static final int TOGGLE_6 = 6; //SHIFT GEARS
	public static final int TOGGLE_7 = 7; //STRAIGHT DRIVE   

	public static final int TOGGLE_12 = 12; //CANCEL ALL
	  
//OPERATOR CONTROLS (JOYSTICK, TOGGLES, BUTTONS) --> X-BOX CONTROLLER

	public static final int JOYSTICK_OPERATOR = 1; //OPER. JOYSTICK PORT

	public static final int LS_X_AXIS = 0; //UNUSED
	public static final int LS_Y_AXIS = 1; //HATCH ARM MOVEMENT
	
	public static final int LT_AXIS = 2; //BALL OUT
	public static final int RT_AXIS = 3; //BALL IN

	public static final int RS_X_AXIS = 4; //UNUSED
	public static final int RS_Y_AXIS = 5; //CARGO ARM MOVEMENT

	public static final int BUTTON_A = 1; //SLOW CARGOARM
	public static final int BUTTON_B = 2; //UNUSED
	public static final int BUTTON_X = 3; //KEEP ARM IN
	public static final int BUTTON_Y = 4; //CANCEL ALL

	public static final int BUTTON_LB = 5; //HATCH OUT
	public static final int BUTTON_RB = 6; //HATCH IN


}
