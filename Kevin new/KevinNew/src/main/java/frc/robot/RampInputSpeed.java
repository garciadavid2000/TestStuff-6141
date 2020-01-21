package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class RampInputSpeed {

    private double maxCPS;
    private double lastSpeed;
    private double lastTime;

    public RampInputSpeed() {} //no use for constuctor, written as part of convention.

    public void setMaxCPS(double CPS) { //set max change per second, (accel. curve)

        this.maxCPS = CPS;

    }

    public double rampSpeed (double input) { //method that actually does ramping.

        //ramping is based on- CPS, and voltage supplied is recalculated
        //every FMS tick, where it ramps at a rate of CPS and is based on the FPGA time.

        if (input > lastSpeed) { //If input is larger than current running speed, run at current speed.

            lastSpeed = Math.min(input, lastSpeed + (Timer.getFPGATimestamp() - lastTime) * maxCPS);

        } else { //If input speed <= current speed, run at input speed or less.

            lastSpeed = Math.max(input, lastSpeed + (Timer.getFPGATimestamp() - lastTime) * maxCPS);
        }

        lastTime = Timer.getFPGATimestamp();

        return lastSpeed;
    }
}