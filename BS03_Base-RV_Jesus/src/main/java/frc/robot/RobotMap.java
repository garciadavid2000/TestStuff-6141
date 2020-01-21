package frc.robot;

/**
 * This class is used to declare sensors and controller ports.
 * Essa classe é usada para declarar portas de sensores e controladores.
 */
public class RobotMap {

  
  /**
   * Joystick port.
   * Portas de joystick. 
  */
  public static final int XBOX_CONTROL_MECHANISM = 1;

  /**
   * Joystick port.
   * Portas de joystick. 
  */
  public static final int XBOX_CONTROL_DRIVER = 0;

  
  /**
   * Drivetrain controllers ports.
   * Portas de motores de tração.
   */
  public static final int MOTOR_FRONT_LEFT = 3;

  /**
   * Drivetrain controllers ports.
   * Portas de motores de tração.
   */
  public static final int MOTOR_BACK_LEFT = 2;

  /**
   * Drivetrain controllers ports.
   * Portas de motores de tração.
   */
  public static final int MOTOR_FRONT_RIGHT = 1;

  /**
   * Drivetrain controllers ports.
   * Portas de motores de tração.
   */
  public static final int MOTOR_BACK_RIGHT = 0;

  /**
   * VictorSPX port (cargo deployment).
   * Porta do VictorSPX da cargo da garra.
   */
  public static final int CLAW_CARGO = 14;
	
  /**
   * VictorSPX port (claw mode).
   * Porta do VictorSPX do modo da garra.
   */
  public static final int CLAW_CONTROL = 4;
  
  public static final int ELEVATOR = 12;

  /**
   * Tower limit's
   * Portas fim de curso da Limit
   */
  
  public static final int LIMIT_TOWER_UP = 6;

  public static final int LIMIT_TOWER_DOWN =7;
  
  /**
   * Claw limit's
   * Portas das chaves fim de curso da garra.
   */
  public static final int LIMIT_CLAW_DOWN = 1;

  public static final int LIMIT_CLAW_UP = 0;

  
  public static final int WHITE_LED_TAPE = 0;

  public static final int GREEN_LED_TAPE = 1; 

  public static final int ENCODER_A = 2;

  public static final int ENCODER_B = 3;

  public static CANHelper CANSensores, CANControladores;

  public static int CAN_SENSORES_DEVICEID = 30;

  public static int CAN_CONTROLADORES_DEVICEID = 31;
}
