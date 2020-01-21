package frc.robot.sensors;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.analog.adis16448.frc.ADIS16448_IMU;

public class GyroClass extends ADXRS450_Gyro {

	public static final ADIS16448_IMU imu = new ADIS16448_IMU();

    double offset = 0.0;
    
	public GyroClass() {

        imu.reset();
        
	}
	
	public void calibrate() {
		
		imu.calibrate();
        
	}
	 
	 @Override 
	 public void reset() {

        setAngle(0.0);
        
	 }
	 
	 /**
	  * Set the gyro to the supplied angle.  This routine will ensure the 
	  * output of the gyro is reset to the supplied angle. If angle is out of 
	  * range, nothing will be done.
	  * @param angle
      */
      
	 public void setAngle(double angle) {

		 if (angle >= 0 && angle < 360) {

			imu.reset();
			 offset = angle; //offset will always be 0.0 at start.
			 
         }
         
	 }

	 @Override 
	 //proccess gyro input angle and gets current angle direction.

	 public double getAngle() {
		 
         double currentAngle = (imu.getAngleX() + offset) % 360.0;
         
		 if (currentAngle < 0.0) {

			//makes negative angles positive
             currentAngle = 360.0 + currentAngle;
             
		 }
		 
		 return(currentAngle);

	 }
	 
	 /** 
	  * Get the error in degrees from the current angle to the target angle. 
	  * This routine calculates the minimum error, always returning 
	  * an angle between -180 degrees and +180 degrees.
	  * @param targetAngle (0 to 360 degrees)
	  * @return angle difference from the current angle (-180 to +180 degrees). 
	  * A positive number indicates the target is clockwise from the current angle.  
	  * A negative number indicates the target is counter-clockwise from the current angle.
	  *
	  */
	 public double getAngleError(double targetAngle) {
		 
		 double error = targetAngle - getAngle();
		 //error will be difference between target and currentAngle. (Makes sense right?)

		 if (error > 180) { // this if Statement computes minimum angleError. (Never > +-180)

			 error -= 360;

		 } else if (error < -180) {

			 error += 360;
		}

		 return error;
		 
	 }
	 
	 public void updateSD() {
		 
		//  SmartDashboard.putNumber("Gyro Angle", getAngle());
		//  SmartDashboard.putData("Gyro", this);
        //  SmartDashboard.putNumber("Gyro Rate", getRate());
		SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
		SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
		
	 }
	 
}
