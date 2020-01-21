/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
 
/**
 * Claw Subsystem. Includes all action methods (moving claws up and dow, deploying cargos, etc.)
 * Subsistemas de Garra. Inclui todos os métodos de ação (movimento da garra, deploy das cargos, entre
 * outros.)
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private int cargoState, clawState;
  private double controlSpeed;
  private VictorSPX victorCargo;
  private Spark victorControl;
  public DigitalInput limitUp;
  public DigitalInput limitDown;
  private final double cargoForce = 0.85;

  @Override
  public void initDefaultCommand() {

  }

  /**
   * Construtor padrão da garra
   * Default claw constructor.
   */
  public Claw() { 
    victorCargo = new VictorSPX(RobotMap.CLAW_CARGO);    
    victorControl = new Spark(RobotMap.CLAW_CONTROL);

    limitUp = new DigitalInput(RobotMap.LIMIT_CLAW_UP);
    limitDown = new DigitalInput(RobotMap.LIMIT_CLAW_DOWN);
  }

  /**
   * Função para interromper o movimento do motor da garra.
   * Function that stops the claw movement.
   */
  public void StopClaw (){
    victorControl.set(0);
    clawState = 0;
    sendCANData();
  }

  /**
   * Função para elevar a garra.
   * Function to make the claw move up.
   */
  public void ClawUp (){
    if (!limitUp.get()){
      victorControl.set(0.8);
      clawState = 1;
      sendCANData();
    }
    else StopClaw();
  }

  /**
   * Função para descer a garra.
   * Function to make the claw move down.
   */
  public void ClawDown (){
    victorControl.set(-0.45);
    clawState = -1;
    sendCANData();
  }
  

  /**
   * Função para puxar a cargo.
   * Function to pull cargos.
   */
  public void pullCargo(){
    victorCargo.set(ControlMode.PercentOutput, cargoForce); 
    cargoState = 1;
    sendCANData();
  }

  /**
   * Função para soltar a cargo.
   * Function to drop cargos.
   */
  public void dropCargo(){
    victorCargo.set(ControlMode.PercentOutput, -cargoForce); 
    cargoState = -1;
    sendCANData();
  }

  /**
   * Função para parar o motor dos roletes.
   * Function to stop the claw intake system.
   */
  public void stopCargo(){
    victorCargo.set(ControlMode.PercentOutput, 0.0); 
    cargoState = 0;
    sendCANData();
  }

  public void sendCANData() {
    byte[] controllerData = RobotMap.CANControladores.readData(Integer.parseInt("2F6404AA", 16));
    byte[] sensorData = RobotMap.CANSensores.readData(Integer.parseInt("1F6404FF", 16));

    if (cargoState == 1) {
      controllerData[0] = (byte) 1;
    } else if (cargoState == 0) {
      controllerData[0] = (byte) 2;
    } else if (cargoState == -1) {
      controllerData[0] = (byte) 3;
    }
    
    if (clawState == 1) {
      controllerData[1] = (byte) 1;
    } else if (clawState == 0) {
      controllerData[1] = (byte) 2;
    } else if (clawState == -1) {
      controllerData[1] = (byte) 3;
    }

    if (limitUp.get() && limitDown.get()) {
      sensorData[1] = (byte) 1;
    } else if (limitUp.get()) {
      sensorData[1] = (byte) 2;
    } else if (limitDown.get()) {
      sensorData[1] = (byte) 3;
    }

    RobotMap.CANControladores.writeData(controllerData, Integer.parseInt("2F6404AA", 16));
  }
}