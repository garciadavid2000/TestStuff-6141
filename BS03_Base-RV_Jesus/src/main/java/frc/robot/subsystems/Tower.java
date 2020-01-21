/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.MoveTower;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;



public class Tower extends Subsystem {
  public double vel;
  public VictorSPX tower;
  public DigitalInput limitUp;
  public DigitalInput limitDown;

  public Tower() {
    tower = new VictorSPX(RobotMap.ELEVATOR);
    limitUp = new DigitalInput(RobotMap.LIMIT_TOWER_UP);
    limitDown = new DigitalInput(RobotMap.LIMIT_TOWER_DOWN);
  }

  public void move(double vel){
    tower.set(ControlMode.PercentOutput, vel);
    this.vel = vel;
    sendCANData();
  }
  
  public void stop() {
    tower.set(ControlMode.PercentOutput, 0);
    this.vel = 0;
    sendCANData();
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveTower()); 
   }

   public void sendCANData() {
    // Consultar documentação no GitHub para cada ID utilizado aqui. 
    byte[] dataSensores = RobotMap.CANSensores.readData(Integer.parseInt("1F6404FF", 16));
    byte[] dataControladores = RobotMap.CANSensores.readData(Integer.parseInt("2F6404FF", 16));

    if (limitUp.get() && limitDown.get()) {
      dataSensores[0] = (byte) 1;
    } else if (limitUp.get()) {
      dataSensores[0] = (byte) 2;
    } else if (limitDown.get()) {
      dataSensores[0] = (byte) 3;
    }

    // Colocar na documentação depois
    if (vel < 0) {
      dataControladores[1] = (byte) 1;
    } else if (vel == 0) {
      dataControladores[1] = (byte) 2;
    } else {
      dataControladores[1] = (byte) 3;
    }

    dataControladores[0] = (byte) Math.abs((vel * 100));

    RobotMap.CANSensores.writeData(dataSensores, Integer.parseInt("1F6404FF", 16));
    RobotMap.CANControladores.writeData(dataControladores, Integer.parseInt("2F6404FF", 16));
   }
}