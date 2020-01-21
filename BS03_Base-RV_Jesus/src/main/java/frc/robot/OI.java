package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
/**
 * <p> the  {@link OI} is used to map the joystic to the controller </p>
 */
public class OI {

    public final Joystick driverController = new Joystick(RobotMap.XBOX_CONTROL_DRIVER);
    public final Joystick driverController2 = new Joystick(RobotMap.XBOX_CONTROL_DRIVER+1);

    // We named the variables based on the xbox controller buttons
    Button buttonA = new JoystickButton(driverController, 1);
    Button buttonB = new JoystickButton(driverController, 2);
    Button buttonY = new JoystickButton(driverController, 4);
    Button buttonLB = new JoystickButton(driverController, 6);
    Button buttonRB = new JoystickButton(driverController, 5);

    /*INICIO DO CODIGO INSTAVEL*/
    //verificar a correspondencia do números
    Button buttonStart = new JoystickButton(driverController, 7);
    Button buttonBack = new JoystickButton(driverController, 8);
    /*FIM DO CODIGO INSTAVEL*/

    //publico não é o padrão, alterar 
    public Button buttonX = new JoystickButton(driverController, 3);


     //ainda é usado? rever
    private static int angle = 45;

    public static void setAngle(int angle) {
        OI.angle = angle;
    } 

    public static int getAngle(){
        return OI.angle;
    }

    public OI(){

        buttonB.whileHeld(new DropCargo()); 
        buttonB.whenReleased(new StopCargo());

        buttonA.whileHeld(new PullCargo());  
        buttonA.whenReleased(new StopCargo());

        buttonLB.whileHeld(new ClawUp());
        buttonLB.whenReleased(new StopClaw());

        buttonRB.whileHeld(new ClawDown());
        buttonRB.whenReleased(new StopClaw());

        buttonY.whenPressed(new GrabHatch());
    }
}
