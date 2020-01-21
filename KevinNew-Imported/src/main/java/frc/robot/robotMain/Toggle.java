package frc.robot.robotMain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Toggle Class that stores values for a toggle object and handles button
 * presses for desired joystick button to make it a toggle
 * custom written, using given methods from FRC. (whenPressed for button.)
 */

	public class Toggle {

		private boolean toggle;
		
		public Toggle(Joystick joystick, int port, boolean start) {

			JoystickButton button = new JoystickButton(joystick, port);

			button.whenPressed(new UpdateToggleCommand());
			toggle = start;
            
		}

		public boolean getToggle() {

			return toggle;
			
		}
		
		public void setToggle(boolean value) {
			toggle = value;
		}
		
		public class UpdateToggleCommand extends InstantCommand {
			@Override
			protected void initialize() {
				toggle = !toggle;
			}
		}		
	}